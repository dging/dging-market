package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.user.QUser;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.dging.dgingmarket.domain.common.QImage.image;
import static com.dging.dgingmarket.domain.common.QTag.tag;
import static com.dging.dgingmarket.domain.product.QFavorite.favorite;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.store.QFollower.*;
import static com.dging.dgingmarket.domain.store.QReview.*;
import static com.dging.dgingmarket.domain.store.QStore.store;
import static com.dging.dgingmarket.domain.user.QUser.*;

public class FollowerQueryRepositoryImpl extends QuerydslRepositorySupport implements FollowerQueryRepository {

    private final JPAQueryFactory queryFactory;

    public FollowerQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Follower.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<FollowersResponse> followers(Pageable pageable, Long storeId, CommonCondition cond) {

        QUser from = new QUser("from");
        QFollower follower = new QFollower("follower");
        QFollower fromFollower = new QFollower("fromFollower");
        QUser fromFrom = new QUser("fromFrom");
        QUser to = new QUser("to");
        QStore fromStore = new QStore("fromStore");
        QStore toStore = new QStore("toStore");

        JPAQuery<FollowersResponse> query = queryFactory
                .select(Projections.constructor(FollowersResponse.class,
                        fromStore.id,
                        fromStore.name,
                        JPAExpressions.select(review.rate.avg().castToNum(Float.class))
                                .from(review)
                                .where(review.store.eq(fromStore))
                                .groupBy(review.id),
                        new CaseBuilder()
                                .when(product.deleted.isFalse().and(product.uploaded.isTrue()))
                                .then(product.id)
                                .otherwise(Expressions.nullExpression())
                                .countDistinct().castToNum(Integer.class),
                        fromFrom.countDistinct().castToNum(Integer.class)
                ))
                .from(fromStore)
                .join(from).on(fromStore.user.eq(from))
                .join(follower).on(follower.from.eq(from))
                .join(to).on(follower.to.eq(to))
                .join(toStore).on(toStore.user.eq(to))
                .leftJoin(product).on(product.store.eq(fromStore))
                .leftJoin(fromFollower).on(fromFollower.to.eq(from))
                .leftJoin(fromFrom).on(fromFollower.from.eq(fromFrom))
                .where(
                        toStore.id.eq(storeId),
                        search(cond.getQuery()),
                        dateGoe(new PathBuilder<>(Follower.class, "follower"), "createdAt", cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Follower.class, "follower"), "createdAt", cond.getDateTo())
                )
                .groupBy(fromStore.id);

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Follower> pathBuilder = new PathBuilder<Follower>(follower.getType(), follower.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<FollowersResponse> queryResult = query.fetch();

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

    private Predicate search(String query) {
        QStore fromStore = new QStore("fromStore");
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(fromStore.name.contains(query));
        return builder;
    }

    private BooleanExpression dateLt(PathBuilder<?> entity, String fieldName, Date dateLt) {
        return dateLt != null ? entity.getDate(fieldName, Date.class).lt(dateLt) : null;
    }

    private BooleanExpression dateGoe(PathBuilder<?> entity, String fieldName, Date dateGoe) {
        return dateGoe != null ? entity.getDate(fieldName, Date.class).goe(dateGoe) : null;
    }

}
