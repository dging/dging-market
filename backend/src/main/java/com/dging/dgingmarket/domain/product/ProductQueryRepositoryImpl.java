package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.type.MainCategory;
import com.dging.dgingmarket.util.param.SearchParam;
import com.dging.dgingmarket.web.api.dto.CommonCondition;
import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.TagsResponse;
import com.dging.dgingmarket.web.api.dto.product.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dging.dgingmarket.domain.QImage.image;
import static com.dging.dgingmarket.domain.QTag.tag;
import static com.dging.dgingmarket.domain.product.QFavorite.favorite;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.product.QProductImage.productImage;
import static com.dging.dgingmarket.domain.product.QProductTag.productTag;
import static com.dging.dgingmarket.domain.store.QStore.store;
import static com.dging.dgingmarket.domain.user.QUser.user;

public class ProductQueryRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<FavoriteProductsResponse> favoriteProducts(Pageable pageable, Long userId, CommonCondition cond) {

        List<Long> ids = queryFactory.select(product.id)
                .from(product)
                .join(store).on(store.eq(product.store))
                .join(user).on(user.eq(store.user))
                .join(favorite).on(favorite.product.eq(product))
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        user.id.eq(userId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> query = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .join(user).on(user.eq(store.user))
                .join(favorite).on(favorite.product.eq(product))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        product.id.in(ids),
                        user.id.eq(userId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .groupBy(product.id, tag.id, image.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Product> pathBuilder = new PathBuilder<Product>(product.getType(), product.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<FavoriteProductsResponse> favoriteProducts = query
                .transform(
                        GroupBy.groupBy(product.id).list(
                                Projections.constructor(FavoriteProductsResponse.class,
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        product.runningStatus,
                                        GroupBy.list(Projections.constructor(ImagesResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.price,
                                        GroupBy.list(Projections.constructor(TagsResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.createdAt
                                ).skipNulls()
                        )
                );

        favoriteProducts.forEach(productsResponse -> {

            List<ImagesResponse> distinctImages = productsResponse.getImages().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setImages(distinctImages);

            List<TagsResponse> distinctTags = productsResponse.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setTags(distinctTags);
        });

        JPAQuery<Long> count = queryFactory.select(product.id)
                .from(product)
                .join(store).on(store.eq(product.store))
                .join(user).on(user.eq(store.user))
                .join(favorite).on(favorite.product.eq(product))
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        user.id.eq(userId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                );

        return PageableExecutionUtils.getPage(favoriteProducts, pageable, count::fetchOne);
    }

    @Override
    public Page<StoreProductsResponse> storeProducts(Pageable pageable, Long storeId, CommonCondition cond, ProductsCondition productsCond) {

        List<Long> ids = queryFactory.select(product.id)
                .from(product)
                .join(store).on(store.eq(product.store))
                .where(
                        product.deleted.isFalse(),
                        store.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        filterByMainCategory(new PathBuilder<>(Product.class, product.getMetadata()), product.mainCategory.getMetadata(), productsCond.getMainCategory()),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> query = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .leftJoin(favorite).on(favorite.product.eq(product))
                .where(
                        product.deleted.isFalse(),
                        product.id.in(ids),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        filterByMainCategory(new PathBuilder<>(Product.class, product.getMetadata()), product.mainCategory.getMetadata(), productsCond.getMainCategory()),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .groupBy(product.id, tag.id, image.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Product> pathBuilder = new PathBuilder<Product>(product.getType(), product.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<StoreProductsResponse> storeProducts = query
                .transform(
                        GroupBy.groupBy(product.id).list(
                                new QStoreProductsResponse(
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        favorite.countDistinct().castToNum(Integer.class),
                                        product.runningStatus,
                                        GroupBy.list(Projections.constructor(ImagesResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.price,
                                        GroupBy.list(Projections.constructor(TagsResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.uploaded.not(),
                                        product.createdAt,
                                        product.updatedAt
                                )
                        )
                );

        storeProducts.forEach(productsResponse -> {

            List<ImagesResponse> distinctImages = productsResponse.getImages().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setImages(distinctImages);

            List<TagsResponse> distinctTags = productsResponse.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setTags(distinctTags);
        });

        JPAQuery<Long> count = queryFactory.select(product.count())
                .from(product)
                .join(store).on(store.eq(product.store))
                .where(
                        product.deleted.isFalse(),
                        store.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        filterByMainCategory(new PathBuilder<>(Product.class, product.getMetadata()), product.mainCategory.getMetadata(), productsCond.getMainCategory()),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                );

        return PageableExecutionUtils.getPage(storeProducts, pageable, count::fetchOne);
    }

    @Override
    public Page<ProductsResponse> products(Pageable pageable, CommonCondition cond) {

        List<Long> ids = queryFactory.select(product.id)
                .from(product)
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Product> query = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        product.id.in(ids),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                )
                .groupBy(product.id, tag.id, image.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Product> pathBuilder = new PathBuilder<Product>(product.getType(), product.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<ProductsResponse> products = query
                .transform(
                        GroupBy.groupBy(product.id).list(
                                Projections.constructor(ProductsResponse.class,
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        product.runningStatus,
                                        GroupBy.list(Projections.constructor(ImagesResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.price,
                                        GroupBy.list(Projections.constructor(TagsResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.createdAt
                                ).skipNulls()
                        )
                );

        products.forEach(productsResponse -> {

            List<ImagesResponse> distinctImages = productsResponse.getImages().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setImages(distinctImages);

            List<TagsResponse> distinctTags = productsResponse.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setTags(distinctTags);
        });

        JPAQuery<Long> count = queryFactory.select(product.count())
                .from(product)
                .where(
                        product.deleted.isFalse(),
                        product.uploaded.isTrue(),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.title.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Product.class, product.getMetadata()), product.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, store.getMetadata()), store.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Product.class, product.getMetadata()), product.createdAt.getMetadata(), cond.getDateTo())
                );

        return PageableExecutionUtils.getPage(products, pageable, count::fetchOne);
    }

    @Override
    public Optional<ProductResponse> product(Long id) {

        Optional<ProductResponse> optional = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .leftJoin(favorite).on(favorite.product.eq(product))
                .where(
                        product.id.eq(id),
                        product.deleted.isFalse()
                )
                .groupBy(product.id, image.id, tag.id)
                .transform(
                        GroupBy.groupBy(product.id).list(
                                new QProductResponse(
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        product.content,
                                        favorite.countDistinct().castToNum(Integer.class),
                                        product.views,
                                        product.quality,
                                        product.quantity,
                                        product.region,
                                        product.location,
                                        product.mainCategory,
                                        product.middleCategory,
                                        product.subCategory,
                                        product.runningStatus,
                                        GroupBy.list(Projections.constructor(ImagesResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.price,
                                        GroupBy.list(Projections.constructor(TagsResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.uploaded.not(),
                                        product.createdAt
                                ).skipNulls()
                        )
                ).stream().findAny();

        optional.ifPresent(queryResult -> {
            List<ImagesResponse> distinctImages = queryResult.getImages().stream()
                    .distinct()
                    .collect(Collectors.toList());
            queryResult.setImages(distinctImages);

            List<TagsResponse> distinctTags = queryResult.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            queryResult.setTags(distinctTags);
        });

        return optional;
    }

    private Predicate search(List<SearchParam> searchParams, String query) {
        BooleanBuilder builder = new BooleanBuilder();

        for (SearchParam searchParam : searchParams) {
            builder.or(searchParam.getEntity().getString(searchParam.getMetadata().getName()).contains(query));
        }

        return builder;
    }

    private Predicate filterByMainCategory(PathBuilder<?> entity, PathMetadata metadata, MainCategory mainCategory) {

        BooleanBuilder builder = new BooleanBuilder();
        if (!ObjectUtils.isEmpty(mainCategory)) {
            builder.and(entity.getEnum(metadata.getName(), MainCategory.class).eq(mainCategory));
        }
        return builder;
    }

    private BooleanExpression dateLt(PathBuilder<?> entity, PathMetadata metadata, Date dateLt) {
        return dateLt != null ? entity.getDate(metadata.getName(), Date.class).lt(dateLt) : null;
    }

    private BooleanExpression dateGoe(PathBuilder<?> entity, PathMetadata metadata, Date dateGoe) {
        return dateGoe != null ? entity.getDate(metadata.getName(), Date.class).goe(dateGoe) : null;
    }
}
