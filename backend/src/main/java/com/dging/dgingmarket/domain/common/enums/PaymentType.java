package com.dging.dgingmarket.domain.common.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter(onMethod_ = {@JsonValue})
@AllArgsConstructor
public enum PaymentType {
    NORMAL("일반"),
    SAFE("안전");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static PaymentType find(String value) {
        return Arrays.stream(values()).filter((paymentType) -> paymentType.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}
