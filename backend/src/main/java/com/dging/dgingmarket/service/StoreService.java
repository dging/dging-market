package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.product.exception.ProductNotFoundException;
import com.dging.dgingmarket.domain.store.*;
import com.dging.dgingmarket.domain.store.exception.*;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.store.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final FollowerRepository followerRepository;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void follow(Long id) {

        User from = EntityUtils.userThrowable();
        User to = userRepository.findByStoreId(id).orElseThrow(StoreNotFoundException::new);

        followerRepository.findByFromAndTo(from, to).ifPresent(follower -> {
            throw AlreadyFollowedException.EXCEPTION;
        });

        //본인을 팔로우 하는 경우 예외
        if(Objects.equals(from.getId(), to.getId())) {
            throw FollowMyselfException.EXCEPTION;
        }

        Follower follower = Follower.create(from, to);
        followerRepository.save(follower);
    }

    @Transactional
    public void unfollow(Long id) {

        User from = EntityUtils.userThrowable();
        User to = userRepository.findByStoreId(id).orElseThrow(StoreNotFoundException::new);

        Follower follower = followerRepository.findByFromAndTo(from, to).orElseThrow(FollowerNotFoundException::new);

        followerRepository.delete(follower);
    }

    public Page<FollowersResponse> followers(Pageable pageable, Long id, CommonCondition cond) {
        return followerRepository.followers(pageable, id, cond);
    }

    public Page<FollowingsResponse> followings(Pageable pageable, Long id, CommonCondition cond) {
        return followerRepository.followings(pageable, id, cond);
    }

    @Transactional
    public void updateIntroduction(Long id, StoreIntroductionChangeRequest request) {

        User user = EntityUtils.userThrowable();
        Store foundStore = storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);

        if (!Objects.equals(foundStore.getId(), user.getStore().getId())) {
            throw UserOwnStoreException.EXCEPTION;
        }

        foundStore.updateIntroduction(request.getIntroduction());
    }

    @Transactional
    public void updateName(Long id, StoreNameChangeRequest request) {

        User user = EntityUtils.userThrowable();
        Store foundStore = storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);

        storeRepository.findByName(request.getName()).ifPresent(store -> {
            if(!Objects.equals(store.getId(), user.getStore().getId()) || Objects.equals(store.getName(), request.getName()))
                throw StoreNameDuplicatedException.EXCEPTION;
        });

        if (!Objects.equals(foundStore.getId(), user.getStore().getId())) {
            throw UserOwnStoreException.EXCEPTION;
        }

        foundStore.updateName(request.getName());
    }

    @Transactional
    public void createProductReview(Long id, StoreReviewCreateRequest request) {

        User user = EntityUtils.userThrowable();
        Product foundProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Review reviewToCreate = Review.create(user, foundProduct, request.getContent(), request.getRating());

        //사용자 본인의 상품에 후기 불가능
        if(Objects.equals(user.getStore().getId(), foundProduct.getStore().getId())) {
            throw ReviewMyselfException.EXCEPTION;
        }

        //TODO: 후기는 하나의 상품에 한 번만 작성 가능하므로 이미 남긴 후기에 대해 예외 처리

        //TODO: 거래가 완료된 사용자에 한해서만 후기 작성 가능하도록 예외 처리

        reviewRepository.save(reviewToCreate);
    }

    @Transactional
    public void deleteProductReview(Long id) {

        User user = EntityUtils.userThrowable();
        Review foundReview = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);

        //사용자 본인이 작성한 후기가 아니면 삭제 불가능
        if(!Objects.equals(user.getId(), foundReview.getUserId())) {
            throw UserOwnReviewException.EXCEPTION;
        }

        reviewRepository.delete(foundReview);
    }

    public Page<StoreProductReviewsResponse> productReviews(Long id, Pageable pageable, @Valid CommonCondition cond) {
        return reviewRepository.storeProductReviews(id, pageable, cond);
    }

    public StoreOverviewResponse overview(Long id) {
        return storeRepository.overview(id).orElseThrow(StoreNotFoundException::new);
    }

    public StoreResponse store(Long id) {

        return storeRepository.store(id).orElseThrow(StoreNotFoundException::new);
    }

    public StoreResponse myStore() {

        User user = EntityUtils.userThrowable();
        Long storeId = user.getStore().getId();

        return storeRepository.store(storeId).orElseThrow(StoreNotFoundException::new);
    }
}
