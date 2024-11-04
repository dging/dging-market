package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import java.io.FileInputStream;
import java.util.Optional;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.requestParts;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileApiControllerTest extends ApiDocumentationTest {

    @BeforeEach
    public void setup() {

        //given(업로드)
        when(imageRepository.saveAndFlush(any())).thenAnswer(i -> {

            Image image = i.getArgument(0, Image.class);

            when(imageRepository.findById(any())).thenReturn(Optional.of(image));

            ReflectionTestUtils.setField(image, "id", 1L);

            return image;
        });
    }

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
            final String originalFilename = "N1.jpg";
            final String filePath = "src/test/resources/static/" + originalFilename;

            MockMultipartFile mockFile = new MockMultipartFile(
                    "image",
                    originalFilename,
                    MediaType.IMAGE_JPEG_VALUE,
                    new FileInputStream(filePath)
            );

            ImagesResponse uploaded = fileUploadService.upload(mockFile, filePath, ImageType.PRODUCT);
            Long imageId = uploaded.getId();

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/files/s3/{id}", imageId)
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
