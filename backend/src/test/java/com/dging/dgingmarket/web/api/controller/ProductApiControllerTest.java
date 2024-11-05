package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.util.RequestFixture;
import com.dging.dgingmarket.util.ResponseFixture;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.product.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.any;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductApiControllerTest extends ApiDocumentationTest {

    @Nested
    @DisplayName("상품 조회")
    class ProductsTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            List<ProductsResponse> products = ResponseFixture.PRODUCTS;
            Page<ProductsResponse> page = new PageImpl<>(products.subList(0, 10), Pageable.unpaged(), products.size());
            given(productService.products(any(), any())).willReturn(page);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products")
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("상품 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader),
                            queryParameters(pageParams),
                            responseFields(pageFields)
                                    .andWithPrefix("content.[].", productsResponseFields)
                                    .andWithPrefix("content.[].images.[].", imagesResponseFields)
                                    .andWithPrefix("content.[].tags.[].", tagResponseFields)));
        }
    }

    @Nested
    @DisplayName("상품 상세 조회")
    class ProductTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            ProductResponse product = ResponseFixture.PRODUCT;

            given(productService.product(any())).willReturn(product);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products/{id}", product.getId())
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("상품 상세 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 수정")
    class UpdateTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            long productId = 1L;
            ProductUpdateRequest request = RequestFixture.PRODUCT_UPDATE;

            willDoNothing().given(productService).update(productId, request);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/products/{id}", productId)
                    .content(mapper.writeValueAsString(request))
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("상품 수정 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 등록")
    class CreateTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            ProductCreateRequest request = RequestFixture.PRODUCT_CREATE;

            willDoNothing().given(productService).create(request);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/products")
                    .content(mapper.writeValueAsString(request))
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isCreated())
                    .andDo(document("상품 등록 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 삭제")
    class DeleteTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            long productId = 1L;

            willDoNothing().given(productService).delete(productId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/products/{id}", productId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isNoContent())
                    .andDo(document("상품 삭제 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 진행 상태 변경")
    class ChangeRunningStatusTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            long productId = 1L;
            ProductRunningStatusChangeRequest request = RequestFixture.PRODUCT_RUNNING_STATUS_CHANGE;

            willDoNothing().given(productService).changeRunningStatus(productId, request.getRunningStatus());

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.patch("/products/{id}", productId)
                    .content(mapper.writeValueAsString(request))
                    .header("Authorization", request)
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("상품 진행 상태 변경 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 찜하기")
    class CreateFavoriteTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            long productId = 1L;
            willDoNothing().given(productService).createFavorite(productId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/products/{productId}/favorite", productId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isCreated())
                    .andDo(document("상품 찜하기 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("상품 찜 해제하기")
    class DeleteFavoriteTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            long productId = 1L;
            willDoNothing().given(productService).deleteFavorite(productId);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/products/{productId}/favorite", productId)
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isNoContent())
                    .andDo(document("상품 찜 해제하기 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

    @Nested
    @DisplayName("찜한 상품 조회")
    class FavoriteProductsTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            List<FavoriteProductsResponse> favoriteProducts = ResponseFixture.FAVORITE_PRODUCTS;
            Page<FavoriteProductsResponse> page = new PageImpl<>(favoriteProducts.subList(0, 10), Pageable.unpaged(), favoriteProducts.size());

            given(productService.favoriteProducts(any(), any())).willReturn(page);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products/favorite")
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("찜한 상품 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }

}
