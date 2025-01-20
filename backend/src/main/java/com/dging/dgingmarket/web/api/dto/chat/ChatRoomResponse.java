package com.dging.dgingmarket.web.api.dto.chat;

import com.dging.dgingmarket.web.api.dto.ImagesResponse;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 *  채팅방 상세 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoomResponse {

    private Long id;
    private Long recipientId;
    private String recipientName;
    private Date lastRecipientConnectedAt;

    private List<ImagesResponse> productImages;
    private String productTitle;
    private int productPrice;
    private boolean isShippingFreeIncluded;

    @QueryProjection
    public ChatRoomResponse(Long id, List<ImagesResponse> productImages, String productTitle, int productPrice, boolean isShippingFreeIncluded) {
        this.id = id;
        this.productImages = productImages;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.isShippingFreeIncluded = isShippingFreeIncluded;
    }
}
