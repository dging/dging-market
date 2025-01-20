package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "최근 상품 조회 응답 DTO")
public class RecentProductsResponse {

    @Schema(description = RESPONSE_ID, example = EXAMPLE_ID)
    private Long id;

    @Schema(description = RESPONSE_IMAGE)
    private ImagesResponse image;

    @Schema(description = RESPONSE_CREATED_AT)
    private Date createdAt;

    @JsonIgnore
    private Long storeId;

    @QueryProjection
    public RecentProductsResponse(Long id, Long imageId, String imageUrl, Long storeId, Date createdAt) {

        if(id == null && imageId == null && imageUrl == null) storeId = null;

        if(imageId != null && imageUrl != null) {
            this.image = new ImagesResponse(imageId, imageUrl);
        }

        this.id = id;
        this.storeId = storeId;
        this.createdAt = createdAt;
    }
}
