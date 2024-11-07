package com.dging.dgingmarket.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum NotificationType {
    FOLLOW("팔로우"),
    NEW_PRODUCT("상품등록"),
    COMMENT("댓글"),
    FAVORITE("찜");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static NotificationType find(String value) {
        return Arrays.stream(values()).filter((notificationType) -> notificationType.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}
