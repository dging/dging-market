package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.store.exception.AlreadyFollowedException;
import com.dging.dgingmarket.domain.store.exception.FollowerNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.store.exception.FollowMyselfException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.ResponseFixture;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.product.ProductsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StoreApiControllerTest extends ApiDocumentationTest {

    @Nested
    @DisplayName("상점 상품 조회")
    class StoreProductsTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            User user = EntityUtils.userThrowable();
            List<StoreProductsResponse> queryResult = ResponseFixture.STORE_PRODUCTS;
            Page<StoreProductsResponse> page = new PageImpl<>(queryResult.subList(0, 10), Pageable.unpaged(), 20);

            given(productService.storeProducts(any(), eq(user.getId()), any(), any())).willReturn(page);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/stores/{id}/products", user.getStore().getId())
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("상점 상품 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상점 팔로우")
    @Transactional
    class FollowTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            Long toId = 2L;
            willDoNothing().given(storeService).follow(toId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/stores/{id}/followers", toId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isCreated())
                    .andDo(document("상점 팔로우 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }

        @Test
        @DisplayName("이미 팔로우한 사용자를 팔로우하려 하면 실패")
        @WithCustomMockUser
        public void AlreadyFollowed_Fail() throws Exception {

            //given
            Long toId = 2L;
            willThrow(AlreadyFollowedException.EXCEPTION).given(storeService).follow(toId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/stores/{id}/followers", toId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andDo(document("상점 팔로우 - 이미 팔로우한 사용자를 팔로우하려 하면 실패",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }

        @Test
        @DisplayName("본인을 팔로우하려 하면 실패")
        @WithCustomMockUser
        public void FollowMyself_Fail() throws Exception {

            //given
            Long toId = 1L;
            willThrow(FollowMyselfException.EXCEPTION).given(storeService).follow(toId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/stores/{id}/followers", toId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isBadRequest())
                    .andDo(document("상점 팔로우 - 본인을 팔로우하려 하면 실패",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상점 언팔로우")
    @Transactional
    class UnfollowTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            Long toId = 2L;
            willDoNothing().given(storeService).unfollow(toId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/stores/{id}/followers", toId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isNoContent())
                    .andDo(document("상점 언팔로우 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }

        @Test
        @DisplayName("팔로우하지 않은 상점을 팔로우하려 하면 실패")
        @WithCustomMockUser
        public void FollowMyself_Fail() throws Exception {

            //given
            Long toId = 2L;
            willThrow(FollowerNotFoundException.EXCEPTION).given(storeService).unfollow(toId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/stores/{id}/followers", toId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isNotFound())
                    .andDo(document("상점 언팔로우 - 팔로우하지 않은 상점을 팔로우하려 하면 실패",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("팔로워 조회")
    @Transactional
    class FollowersTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            Long storeId = 1L;
            List<FollowersResponse> followers = ResponseFixture.FOLLOWERS;
            Page<FollowersResponse> page = new PageImpl<>(followers.subList(0, 10), Pageable.unpaged(), followers.size());
            given(storeService.followers(any(), eq(storeId), any())).willReturn(page);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/stores/{id}/followers", storeId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("팔로워 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }

    }

    @Nested
    @DisplayName("팔로잉 조회")
    @Transactional
    class FollowingsTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            Long storeId = 1L;
            List<FollowingsResponse> followings = ResponseFixture.FOLLOWINGS;
            Page<FollowingsResponse> page = new PageImpl<>(followings.subList(0, 10), Pageable.unpaged(), followings.size());
            given(storeService.followings(any(), eq(storeId), any())).willReturn(page);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/stores/{id}/followings", storeId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("팔로잉 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }

    }

}
