package com.dging.dgingmarket.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum MainCategory {

    CD("CD"),
    VINYL("바이닐"),
    CASSETTE("카세트"),
    DVD("DVD");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static MainCategory find(String value) {
        return Arrays.stream(values())
                .filter((mainCategory) -> Objects.equals(mainCategory.value, value))
                .findAny()
                .orElse(null);
    }
}
