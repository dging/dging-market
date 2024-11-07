package com.dging.dgingmarket.util.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY,
        description = "0부터 시작하는 페이지 인덱스 (0..N)",
        name = "page", // !
        schema = @Schema(type = "integer", defaultValue = "0"))
@Parameter(in = ParameterIn.QUERY,
        description = "반환할 페이지의 크기",
        name = "size", // !
        schema = @Schema(type = "integer", defaultValue = "20"))
@Parameter(in = ParameterIn.QUERY,
        description = "정렬 기준: 속성(,asc|desc) 형식입니다. 기본 정렬 순서는 오름차순이며, 여러 개의 정렬 기준을 지원합니다.",
        name = "sort",
        array = @ArraySchema(schema = @Schema(type = "string"), arraySchema = @Schema(defaultValue = "[\"createdAt,desc\"]")))
public @interface CustomPageableParameter {

    Class<?> sortCriteria() default void.class;
}