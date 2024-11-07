
package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.product.QFavorite;
import com.dging.dgingmarket.web.api.dto.store.*;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

import static com.dging.dgingmarket.domain.product.QFavorite.*;
import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.store.QFollower.follower;
import static com.dging.dgingmarket.domain.store.QReview.review;
import static com.dging.dgingmarket.domain.store.QStore.store;
import static com.dging.dgingmarket.domain.user.QUser.user;

public class StoreQueryRepositoryImpl extends QuerydslRepositorySupport implements StoreQueryRepository {

    private final JPAQueryFactory queryFactory;

    public StoreQueryRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Store.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<StoreOverviewResponse> overview(Long id) {

        QStore reviewStore = new QStore("reviewStore");

        StoreOverviewResponse overview = queryFactory.select(new QStoreOverviewResponse(
                        store.id,
                        store.name,
                        JPAExpressions.select(product.count().castToNum(Integer.class))
                                .from(product)
                                .where(product.store.eq(store)),
                        JPAExpressions.select(follower.from.count().castToNum(Integer.class))
                                .from(follower)
                                .where(follower.to.eq(store.user))
                ))
                .from(store)
                .where(store.id.eq(id))
                .fetchOne();

        List<StoreProductReviewsResponse> reviews = queryFactory.select(new QStoreProductReviewsResponse(
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
                .where(store.id.eq(id))
                .orderBy(review.createdAt.desc())
                .limit(2)
                .fetch();

        if (overview != null) {
            overview.setReviews(reviews);
        }

        return Optional.ofNullable(overview);
    }

    @Override
    public Optional<StoreResponse> store(Long id) {

        return queryFactory.select(new QStoreResponse(
                        store.id,
                        user.id,
                        store.name,
                        store.introduction,
                        user.thumbnailUrl,
                        JPAExpressions.select(
                                        new CaseBuilder().when(review.isNotNull())
                                                .then(review.rate.avg().castToNum(Float.class))
                                                .otherwise(Expressions.nullExpression())
                                )
                                .from(review)
                                .where(review.store.eq(store)),
                        JPAExpressions.select(product.count().castToNum(Integer.class))
                                .from(product)
                                .where(product.store.eq(store)),
                        user.isAuthenticated,
                        JPAExpressions.select(review.count().castToNum(Integer.class))
                                .from(review)
                                .where(review.store.eq(store)),
                        JPAExpressions.select(favorite.count().castToNum(Integer.class))
                                .from(favorite)
                                .where(favorite.user.eq(user)),
                        JPAExpressions.select(follower.count().castToNum(Integer.class))
                                .from(follower)
                                .where(follower.from.eq(user)),
                        JPAExpressions.select(follower.count().castToNum(Integer.class))
                                .from(follower)
                                .where(follower.to.eq(user)),
                        store.createdAt
                ))
                .from(store)
                .join(user).on(store.user.eq(user))
                .where(store.id.eq(id))
                .stream().findAny();
    }
}
