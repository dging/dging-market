package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.product.QProduct;
import com.dging.dgingmarket.util.param.SearchParam;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.QRecentProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.RecentProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
import com.dging.dgingmarket.web.api.dto.store.QFollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.QFollowingsResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.dging.dgingmarket.domain.common.QImage.image;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.product.QProductImage.productImage;
import static com.dging.dgingmarket.domain.store.QFollower.follower;
import static com.dging.dgingmarket.domain.store.QReview.review;
import static com.dging.dgingmarket.domain.store.QStore.store;

public class FollowerQueryRepositoryImpl extends QuerydslRepositorySupport implements FollowerQueryRepository {

    private final JPAQueryFactory queryFactory;

    public FollowerQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Follower.class);
        this.queryFactory = queryFactory;
    }

    //TODO: image 조회 쿼리에 대해 생성일자 기준 필터링이 아닌 LIMIT 3이 되도록 수정
    @Override
    public Page<FollowersResponse> followers(Pageable pageable, Long storeId, CommonCondition cond) {

        QFollower fromFollower = new QFollower("fromFollower");
        QStore fromStore = new QStore("fromStore");

        List<Long> ids = queryFactory.select(fromStore.id)
                .from(fromStore)
                .join(follower).on(follower.from.eq(fromStore.user))
                .where(
                        follower.to.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, fromStore.getMetadata()), fromStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<FollowersResponse> query = queryFactory
                .select(new QFollowersResponse(
                        fromStore.id,
                        fromStore.name,
                        JPAExpressions.select(
                                        new CaseBuilder().when(review.isNotNull())
                                                .then(review.rate.avg().castToNum(Float.class))
                                                .otherwise(Expressions.nullExpression())
                                )
                                .from(review)
                                .where(review.store.eq(fromStore)),
                        JPAExpressions.select(product.count().castToNum(Integer.class))
                                .from(product)
                                .where(product.store.eq(fromStore)),
                        JPAExpressions.select(fromFollower.from.count().castToNum(Integer.class))
                                .from(fromFollower)
                                .where(fromFollower.to.eq(fromStore.user))
                ))
                .from(fromStore)
                .join(follower).on(follower.from.eq(fromStore.user))
                .where(
                        fromStore.id.in(ids),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, fromStore.getMetadata()), fromStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                )
                .groupBy(fromStore.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Follower> pathBuilder = new PathBuilder<Follower>(follower.getType(), follower.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<FollowersResponse> followers = query.fetch();

        JPAQuery<Long> count = queryFactory.select(fromStore.count())
                .from(fromStore)
                .join(follower).on(follower.from.eq(fromStore.user))
                .where(
                        follower.to.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, fromStore.getMetadata()), fromStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                );

        return PageableExecutionUtils.getPage(followers, pageable, count::fetchOne);
    }

    @Override
    public Page<FollowingsResponse> followings(Pageable pageable, Long storeId, CommonCondition cond) {

        QFollower toFollower = new QFollower("toFollower");
        QStore toStore = new QStore("toStore");
        QProduct subProduct = new QProduct("subProduct");

        List<Long> ids = queryFactory.select(toStore.id)
                .from(toStore)
                .join(follower).on(follower.to.eq(toStore.user))
                .where(
                        follower.from.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, toStore.getMetadata()), toStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<FollowingsResponse> query = queryFactory.select(new QFollowingsResponse(
                        toStore.id,
                        toStore.name,
                        JPAExpressions.select(product.count().castToNum(Integer.class))
                                .from(product)
                                .where(product.store.eq(toStore)),
                        JPAExpressions.select(toFollower.from.count().castToNum(Integer.class))
                                .from(toFollower)
                                .where(toFollower.to.eq(toStore.user)),
                        toStore.createdAt
                ))
                .from(toStore)
                .join(follower).on(follower.to.eq(toStore.user))
                .where(
                        toStore.id.in(ids),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, toStore.getMetadata()), toStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                )
                .groupBy(toStore.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Follower> pathBuilder = new PathBuilder<Follower>(follower.getType(), follower.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<FollowingsResponse> followings = query.fetch();

        List<Long> followingStoreIds = followings.stream().map(FollowingsResponse::getStoreId).toList();

//        WindowFunction<Long> f = JPQLNextExpressions.rowNumber().over().partitionBy().orderBy(subProduct.createdAt.desc());

        List<RecentProductsResponse> recentProducts = queryFactory.select(new QRecentProductsResponse(
                        product.id,
                        image.id,
                        image.url,
                        store.id,
                        product.createdAt
                ).skipNulls())
                .from(store)
                .leftJoin(product).on(
                        product.store.eq(store),
                        product.deleted.isFalse(), product.uploaded.isTrue(),
                        product.in(
                                JPAExpressions.selectFrom(subProduct)
                                        .where(
                                                subProduct.store.eq(store),
                                                subProduct.createdAt.goe(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).minusMonths(1).toInstant()))
                                        )
                        )
                )
                .leftJoin(productImage).on(
                        productImage.product.eq(product),
                        productImage.priority.eq(
                                JPAExpressions.select(productImage.priority.min())
                                        .from(productImage)
                                        .where(productImage.product.eq(product))
                        )
                )
                .leftJoin(image).on(productImage.image.eq(image))
                .where(store.id.in(followingStoreIds))
                .orderBy(product.createdAt.desc())
                .fetch();

        followings.forEach(following -> {
            following.setRecentProducts(recentProducts.stream()
                    .filter(recentProduct -> Objects.equals(recentProduct.getStoreId(), following.getStoreId()))
                    .sorted(Comparator.comparing(RecentProductsResponse::getId).reversed())
                    .limit(3)
                    .toList());
        });

        JPAQuery<Long> count = queryFactory.select(toStore.count())
                .from(toStore)
                .join(follower).on(follower.to.eq(toStore.user))
                .where(
                        follower.from.id.eq(storeId),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Store.class, toStore.getMetadata()), toStore.name.getMetadata())
                                ),
                                cond.getQuery()
                        ),
                        dateGoe(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, follower.getMetadata()), follower.createdAt.getMetadata(), cond.getDateTo())
                );

        return PageableExecutionUtils.getPage(followings, pageable, count::fetchOne);
    }

    private Predicate search(List<SearchParam> searchParams, String query) {
        BooleanBuilder builder = new BooleanBuilder();

        for (SearchParam searchParam : searchParams) {
            builder.or(searchParam.getEntity().getString(searchParam.getMetadata().getName()).contains(query));
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
