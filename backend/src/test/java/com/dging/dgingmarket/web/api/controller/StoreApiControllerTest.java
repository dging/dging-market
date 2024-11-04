package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
import com.dging.dgingmarket.web.api.dto.product.StoreProductsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.EXAMPLE_FAVORITE_COUNT;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.EXAMPLE_PRICE;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
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
            List<StoreProductsResponse> queryResult = LongStream.range(1L, 20L).mapToObj(i ->
                    new StoreProductsResponse(
                            i, i, "storeName", "title", Integer.parseInt(EXAMPLE_FAVORITE_COUNT), RunningStatus.AVAILABLE,
                            List.of(new ImagesResponse(2 * i - 1, "url"), new ImagesResponse(2 * i, "url")), Integer.parseInt(EXAMPLE_PRICE),
                            List.of(new TagsResponse(2 * i - 1, "tag"), new TagsResponse(2 * i, "tag")),
                            new Date(), new Date()
                    )).toList();

            Page<StoreProductsResponse> page = new PageImpl<>(queryResult.subList((int) 0, 10), Pageable.unpaged(), 20);

            when(productRepository.storeProducts(any(), eq(user.getId()), any())).thenReturn(page);

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

}
