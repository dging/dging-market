package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.QImage;
import com.dging.dgingmarket.domain.common.QTag;
import com.dging.dgingmarket.domain.store.QStore;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException.CProductNotFoundException;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.common.ImageResponse;
import com.dging.dgingmarket.web.api.dto.common.TagResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductResponse;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dging.dgingmarket.domain.common.QImage.image;
import static com.dging.dgingmarket.domain.common.QTag.tag;
import static com.dging.dgingmarket.domain.product.QFavorite.favorite;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.product.QProductImage.productImage;
import static com.dging.dgingmarket.domain.product.QProductTag.productTag;
import static com.dging.dgingmarket.domain.store.QStore.store;

public class ProductQueryRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Product.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<ProductsResponse> products(Pageable pageable, CommonCondition cond) {

        JPAQuery<Product> query = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .distinct()
                .where(
                        search(cond.getQuery()),
                        dateGoe(cond.getDateFrom()),
                        dateLt(cond.getDateTo())
                );

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Product> pathBuilder = new PathBuilder<Product>(product.getType(), product.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        if(pageable.getSort().isEmpty()) {
            query = query.orderBy(product.createdAt.desc());
        }

        List<ProductsResponse> queryResult = query
                .transform(
                        GroupBy.groupBy(product.id).list(
                                Projections.constructor(ProductsResponse.class,
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        product.runningStatus,
                                        product.price,
                                        GroupBy.list(Projections.constructor(ImageResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        GroupBy.list(Projections.constructor(TagResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.createdAt
                                ).skipNulls()
                        )
                );

        queryResult.forEach(productsResponse -> {

            List<ImageResponse> distinctImages = productsResponse.getImageUrls().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setImageUrls(distinctImages);

            List<TagResponse> distinctTags = productsResponse.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            productsResponse.setTags(distinctTags);
        });

        int totalCount = queryResult.size();

        long offset = pageable.getOffset();

        if(offset > totalCount) {
            return new PageImpl(Collections.EMPTY_LIST, pageable, totalCount);
        }

        int limit = pageable.getPageSize() * (pageable.getPageNumber() + 1);

        if (limit > totalCount) {
            limit = totalCount;
        }

        return new PageImpl(queryResult.subList((int) offset, limit), pageable, totalCount);
    }

    @Override
    public Optional<ProductResponse> product(Long id) {

        QProduct sqProduct = new QProduct("sqProduct");

        Optional<ProductResponse> optional = queryFactory.selectFrom(product)
                .join(store).on(store.eq(product.store))
                .leftJoin(productImage).on(productImage.product.eq(product))
                .leftJoin(image).on(image.eq(productImage.image))
                .leftJoin(productTag).on(productTag.product.eq(product))
                .leftJoin(tag).on(tag.eq(productTag.tag))
                .where(product.id.eq(id))
                .transform(
                        GroupBy.groupBy(product.id).list(
                                Projections.constructor(ProductResponse.class,
                                        product.id,
                                        store.id,
                                        store.name,
                                        product.title,
                                        product.content,
                                        JPAExpressions.select(favorite.count().castToNum(Integer.class))
                                                .from(sqProduct)
                                                .leftJoin(favorite).on(favorite.product.eq(sqProduct))
                                                .where(sqProduct.id.eq(id)),
                                        product.views,
                                        product.quality,
                                        product.quantity,
                                        product.region,
                                        product.location,
                                        product.mainCategory,
                                        product.middleCategory,
                                        product.subCategory,
                                        product.runningStatus,
                                        GroupBy.list(Projections.constructor(ImageResponse.class,
                                                image.id,
                                                image.url).skipNulls()),
                                        product.price,
                                        GroupBy.list(Projections.constructor(TagResponse.class,
                                                tag.id,
                                                tag.name).skipNulls()),
                                        product.createdAt
                                ).skipNulls()
                        )
                ).stream().findAny();

        optional.ifPresent(queryResult -> {
            List<ImageResponse> distinctImages = queryResult.getImageUrls().stream()
                    .distinct()
                    .collect(Collectors.toList());
            queryResult.setImageUrls(distinctImages);

            List<TagResponse> distinctTags = queryResult.getTags().stream()
                    .distinct()
                    .collect(Collectors.toList());
            queryResult.setTags(distinctTags);
        });

        return optional;
    }

    private Predicate search(String query) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(product.title.contains(query)).or(store.name.contains(query));
        return builder;
    }

    private BooleanExpression dateLt(Date dateLt) {
        return dateLt != null ? product.createdAt.lt(dateLt) : null;
    }

    private BooleanExpression dateGoe(Date dateGoe) {
        return dateGoe != null ? product.createdAt.goe(dateGoe) : null;
    }
}
