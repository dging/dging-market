package com.dging.dgingmarket.web.api.dto.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  알림 내역 조회 응답 DTO
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NotificationsResponse {

    private Long id;
    private Long relatedId;
    private String title;
    private String content;
    private String thumbnailUrl;
}
