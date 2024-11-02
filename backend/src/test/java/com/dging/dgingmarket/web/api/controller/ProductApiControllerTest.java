package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductApiControllerTest extends ApiDocumentationTest {

    @Nested
    @DisplayName("조회")
    class Posts {

        @Test
        @DisplayName("성공")
        @WithMockUser
        public void Success() throws Exception {

            //given

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products")
                    .header("Authorization", "abc")
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
                                    .andWithPrefix("content.[].imageUrls.[].", imageResponseFields)
                                    .andWithPrefix("content.[].tags.[].", tagResponseFields)));
        }
    }
}
