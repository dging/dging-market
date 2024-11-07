
package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.store.QStoreOverviewResponse;
import com.dging.dgingmarket.web.api.dto.store.QStoreProductReviewsResponse;
import com.dging.dgingmarket.web.api.dto.store.StoreOverviewResponse;
import com.dging.dgingmarket.web.api.dto.store.StoreProductReviewsResponse;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

import static com.dging.dgingmarket.domain.product.QProduct.product;
import static com.dging.dgingmarket.domain.store.QFollower.*;
import static com.dging.dgingmarket.domain.store.QReview.review;
import static com.dging.dgingmarket.domain.store.QStore.*;
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

        if(overview != null) {
            overview.setReviews(reviews);
        }

        return Optional.ofNullable(overview);
    }
}
