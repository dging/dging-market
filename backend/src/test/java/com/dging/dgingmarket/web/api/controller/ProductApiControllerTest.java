package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.common.Image;
import com.dging.dgingmarket.domain.common.Tag;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.util.enums.ImageType;
import com.dging.dgingmarket.util.enums.RunningStatus;
import com.dging.dgingmarket.web.api.base.ApiDocumentationTest;
import com.dging.dgingmarket.web.api.dto.common.ImagesResponse;
import com.dging.dgingmarket.web.api.dto.common.TagsResponse;
import com.dging.dgingmarket.web.api.dto.product.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.EXAMPLE_FAVORITE_COUNT;
import static com.dging.dgingmarket.util.constant.DocumentDescriptions.EXAMPLE_PRICE;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
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
            List<ProductsResponse> queryResult = LongStream.range(1L, 20L).mapToObj(i ->
                    new ProductsResponse(
                            i, i, "storeName", "title", RunningStatus.AVAILABLE, 0,
                            List.of(new ImagesResponse(2 * i - 1, "url"), new ImagesResponse(2 * i, "url")),
                            List.of(new TagsResponse(2 * i - 1, "tag"), new TagsResponse(2 * i, "tag")),
                            new Date()
                    )).toList();

            Page<ProductsResponse> page = new PageImpl<>(queryResult.subList(0, 10), Pageable.unpaged(), 20);

            when(productRepository.products(any(), any())).thenReturn(page);

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

        private ProductResponse product;

        @Test
        @DisplayName("성공")
        @WithCustomMockUser
        public void Success() throws Exception {

            //given
            ProductResponse product = ProductResponse.example();

            when(productRepository.product(any())).thenReturn(Optional.of(product));

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products/{id}", 1L)
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
            User user = EntityUtils.userThrowable();
            ProductResponse productResponse = ProductResponse.example();
            Product product = productResponse.toProductWith();
            ReflectionTestUtils.setField(product, "storeId", user.getId());

            when(productRepository.findByIdAndDeletedIsFalse(any())).thenReturn(Optional.of(product));

//            ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

            ProductUpdateRequest request = ProductUpdateRequest.of(product);
            request.setTitle("변경된 제목");
            request.setContent("변경된 내용");

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.put("/products/{id}", productResponse.getId())
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

//            verify(productRepository).save(captor.capture());

//            Product updatedProduct = captor.getValue();
//            assertEquals("변경된 제목", updatedProduct.getTitle());
//            assertEquals("변경된 내용", updatedProduct.getContent());
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
            ProductCreateRequest request = ProductCreateRequest.example();

            User user = EntityUtils.userThrowable();

            List<Image> images = request.getImageIds().stream()
                    .map(i -> {
                        Image image = Image.create(user, ImageType.PRODUCT, "fileName", "path", "url", 0);
                        ReflectionTestUtils.setField(image, "id", i);
                        return image;
                    }).toList();

            List<String> tagsRequest = request.getTags();
            List<Tag> tags = LongStream.rangeClosed(1, tagsRequest.size())
                    .mapToObj(i -> {
                        Tag tag = Tag.create(tagsRequest.get(Long.valueOf(i - 1).intValue()));
                        ReflectionTestUtils.setField(tag, "id", i);
                        return tag;
                    }).toList();

            when(imageRepository.findByIdIn(any())).thenReturn(images);
            when(tagRepository.findByNameIn(any())).thenReturn(tags);

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
            User user = EntityUtils.userThrowable();
            ProductResponse productResponse = ProductResponse.example();
            Product product = productResponse.toProductWith();
            ReflectionTestUtils.setField(product, "storeId", user.getId());

            when(productRepository.findByIdAndDeletedIsFalse(any())).thenReturn(Optional.of(product));

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/products/{id}", productResponse.getId())
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
            User user = EntityUtils.userThrowable();
            ProductResponse productResponse = ProductResponse.example();
            Product product = productResponse.toProductWith();
            ReflectionTestUtils.setField(product, "storeId", user.getId());

            when(productRepository.findByIdAndDeletedIsFalse(any())).thenReturn(Optional.of(product));

            ProductRunningStatusChangeRequest request = ProductRunningStatusChangeRequest.example();

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.patch("/products/{id}", productResponse.getId())
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
            User user = EntityUtils.userThrowable();
            ProductResponse productResponse = ProductResponse.example();
            Product product = productResponse.toProductWith();
            ReflectionTestUtils.setField(product, "storeId", user.getId());

            when(productRepository.findByIdAndDeletedIsFalse(any())).thenReturn(Optional.of(product));
            when(userRepository.findById(any())).thenReturn(Optional.of(user));

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.post("/products/{productId}/favorite", productResponse.getId())
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
            User user = EntityUtils.userThrowable();
            ProductResponse productResponse = ProductResponse.example();
            Product product = productResponse.toProductWith();
            ReflectionTestUtils.setField(product, "storeId", user.getId());

            when(productRepository.findByIdAndDeletedIsFalse(any())).thenReturn(Optional.of(product));
            when(userRepository.findById(any())).thenReturn(Optional.of(user));

            //when
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.delete("/products/{productId}/favorite", productResponse.getId())
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
            User user = EntityUtils.userThrowable();
            List<FavoriteProductsResponse> queryResult = LongStream.range(1L, 20L).mapToObj(i ->
                    new FavoriteProductsResponse(
                            i, i, "storeName", "title", RunningStatus.AVAILABLE,
                            List.of(new ImagesResponse(2 * i - 1, "url"), new ImagesResponse(2 * i, "url")), 0,
                            List.of(new TagsResponse(2 * i - 1, "tag"), new TagsResponse(2 * i, "tag")),
                            new Date()
                    )).toList();

            Page<FavoriteProductsResponse> page = new PageImpl<>(queryResult.subList((int) 0, 10), Pageable.unpaged(), 20);

            when(productRepository.favoriteProducts(any(), eq(user.getId()), any())).thenReturn(page);

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
            ResultActions result = mockMvc.perform(RestDocumentationRequestBuilders.get("/products/favorite")
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
