package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.product.QProduct;
import com.dging.dgingmarket.domain.user.QUser;
import com.dging.dgingmarket.util.param.SearchParam;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.product.QRecentProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.RecentProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.dging.dgingmarket.domain.common.QImage.image;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.product.QProductImage.productImage;
import static com.dging.dgingmarket.domain.store.QFollower.follower;
import static com.dging.dgingmarket.domain.store.QReview.review;
import static com.dging.dgingmarket.domain.store.QStore.store;
import static com.dging.dgingmarket.domain.user.QUser.*;

public class ReviewQueryRepositoryImpl extends QuerydslRepositorySupport implements ReviewQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ReviewQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Review.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<StoreProductReviewsResponse> storeProductReviews(Long id, Pageable pageable, CommonCondition cond) {

        QStore reviewStore = new QStore("reviewStore");

        JPAQuery<StoreProductReviewsResponse> query = queryFactory.select(new QStoreProductReviewsResponse(
                        review.id,
                        reviewStore.id,
                        reviewStore.name,
                        user.thumbnailUrl,
                        review.rate,
                        product.id,
                        product.title,
                        review.content,
                        review.createdAt
                ))
                .from(review)
                .join(store).on(review.store.eq(store))
                .join(user).on(review.user.eq(user))
                .join(reviewStore).on(reviewStore.user.eq(user))
                .join(product).on(review.product.eq(product))
                .where(
                        store.id.eq(id),
                        search(
                                List.of(
                                        new SearchParam(new PathBuilder<>(Review.class, review.getMetadata()), review.content.getMetadata()),
                                        new SearchParam(new PathBuilder<>(Store.class, reviewStore.getMetadata()), reviewStore.name.getMetadata())
                                ),
                                cond.getQuery()),
                        dateGoe(new PathBuilder<>(Review.class, review.getMetadata()), review.createdAt.getMetadata(), cond.getDateFrom()),
                        dateLt(new PathBuilder<>(Review.class, review.getMetadata()), review.createdAt.getMetadata(), cond.getDateTo())
                )
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder<? extends Review> pathBuilder = new PathBuilder<Review>(review.getType(), review.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        List<StoreProductReviewsResponse> reviews = query.fetch();

        JPAQuery<Long> count = queryFactory.select(review.count())
                .from(review)
                .where(review.store.id.eq(id));

        return PageableExecutionUtils.getPage(reviews, pageable, count::fetchOne);
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
