package com.dging.dgingmarket.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RunningStatus {
    AVAILABLE("판매중"),
    BOOKED("예약중"),
    COMPLETE("거래완료");

    private String value;

    @Override
    public String toString() {
        return value;
    }

    public static RunningStatus find(String value) {
        return Arrays.stream(values()).filter((runningStatus) -> runningStatus.value.equals(value)).findAny().orElseThrow(RuntimeException::new);
    }
}