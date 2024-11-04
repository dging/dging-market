package com.dging.dgingmarket.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum ProductQuality {
    BEST("최상"),
    HIGH("상"),
    MEDIUM("중");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static ProductQuality find(String value) {
        return Arrays.stream(values()).filter((productQuality) -> productQuality.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}
