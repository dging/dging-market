package com.dging.dgingmarket.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum ImageType {
    PRODUCT("상품"),
    CHAT("채팅");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static ImageType find(String value) {
        return Arrays.stream(values()).filter((imageType) -> imageType.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}
