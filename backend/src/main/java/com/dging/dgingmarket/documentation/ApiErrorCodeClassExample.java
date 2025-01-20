package com.dging.dgingmarket.documentation;

import com.dging.dgingmarket.exception.BaseErrorCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeClassExample {

    Class<? extends BaseErrorCode> value();
}
