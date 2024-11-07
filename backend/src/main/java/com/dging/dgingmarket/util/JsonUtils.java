package com.dging.dgingmarket.util;

import lombok.SneakyThrows;

import java.util.Date;

public class JsonUtils {

    @SneakyThrows
    public static String jsonTypeFrom(Class<?> javaType) {

        if (javaType == String.class || javaType == Date.class) {
            return "string";
        } else if (javaType == Boolean.class || javaType == boolean.class) {
            return "boolean";
        } else if (javaType == Integer.class || javaType == int.class ||
                javaType == Long.class || javaType == long.class ||
                javaType == Float.class || javaType == float.class ||
                javaType == Double.class || javaType == double.class) {
            return "number";
        } else if (javaType.isArray()) {
            return "array";
        } else {
            return "unknown";
        }
    }
}
