package com.dging.dgingmarket.util.param;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchParam {

    public PathBuilder<?> entity;
    public PathMetadata metadata;
}
