package com.dging.dgingmarket.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum ChatRoomType {
    PURCHASE("구매"),
    SALES("판매"),
    PURCHASE_SALES("구매/판매");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static ChatRoomType find(String value) {
        return Arrays.stream(values()).filter((chatRoomType) -> chatRoomType.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}
