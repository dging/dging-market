package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductApiControllerTest extends ApiDocumentationTest {

    @Nested
    @DisplayName("상품 조회")
    class Products {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products")
                    .header("Authorization", "Bearer [토큰 입력]")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result
                    .andDo(print())
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

    @Nested
    @DisplayName("이미지 업로드")
    class UploadImage {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            User user = User.createMock(1L, "password", List.of(Role.USER));
            Image image = Image.createMock(user);
            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(imageRepository.saveAndFlush(any())).thenReturn(image);

            MockMultipartFile mockFile = new MockMultipartFile(
                    "image",
                    "test.jpg",
                    MediaType.IMAGE_JPEG_VALUE,
                    "image content".getBytes()
            );

            //when
            MockMultipartHttpServletRequestBuilder builder = RestDocumentationRequestBuilders.multipart("/files/s3");

            builder.with(request -> {
                request.setMethod("POST");
                return request;
            });

            ResultActions result = mockMvc.perform(builder
                    .file(mockFile)
                    .part(new MockPart("type", ImageType.PRODUCT.getValue().getBytes()))
                    .header("Authorization", "Bearer")
                    .contentType(MediaType.MULTIPART_FORM_DATA));

            //then
            result.andDo(print())
                    .andExpect(status().isCreated())
                    .andDo(document("이미지 업로드 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader),
                            requestParts(imageRequestFields),
                            responseFields(imageResponseFields)));
        }
    }
}
