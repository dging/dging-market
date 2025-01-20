package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.util.ResponseFixture;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.domain.type.ImageType;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.ImageResponse;
import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.io.FileInputStream;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileApiControllerTest extends ApiDocumentationTest {

    @Nested
    @DisplayName("이미지 업로드")
    class UploadImageTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            final String originalFilename = "N1.jpg";
            final String filePath = "src/test/resources/static/" + originalFilename;

            MockMultipartFile mockFile = new MockMultipartFile(
                    "image",
                    originalFilename,
                    MediaType.IMAGE_JPEG_VALUE,
                    new FileInputStream(filePath)
            );

            ImagesResponse images = ImagesResponse.builder()
                    .id(Long.parseLong(DocumentDescriptions.EXAMPLE_ID))
                    .url("http://www.example.com/" + filePath)
                    .build();
            given(fileUploadService.upload(any(), any(), any())).willReturn(images);

            //when
            MockMultipartHttpServletRequestBuilder builder = RestDocumentationRequestBuilders.multipart("/files/s3");

            builder.with(request -> {
                request.setMethod("POST");
                return request;
            });

            ResultActions result = mockMvc.perform(builder
                    .file(mockFile)
                    .part(new MockPart("type", ImageType.PRODUCT.getValue().getBytes()))
                    .header("Authorization", "")
                    .contentType(MediaType.MULTIPART_FORM_DATA));

            //then
            result.andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andDo(document("이미지 업로드 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader),
                            requestParts(imageRequestFields),
                            responseFields(imagesResponseFields)));
        }
    }

    @Nested
    @DisplayName("이미지 상세 조회")
    class ImageTest {

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            ImageResponse image = ResponseFixture.IMAGE;
            given(fileUploadService.image(eq(image.getId()))).willReturn(image);

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/files/s3/{id}", image.getId())
                    .header("Authorization", "")
                    .contentType(MediaType.APPLICATION_JSON));

            //then
            result.andDo(print())
                    .andExpect(status().isOk())
                    .andDo(document("이미지 상세 조회 - 성공",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(jwtHeader)));
        }
    }
}
