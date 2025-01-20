package com.dging.dgingmarket.web.api.dto;

import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@Schema(description = "공통 조회 조건 DTO")
public class CommonCondition {


    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Schema(description = DocumentDescriptions.CONDITION_DATE_FROM)
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Schema(description = DocumentDescriptions.CONDITION_DATE_TO)
    private Date dateTo;

    @Schema(description = DocumentDescriptions.CONDITION_QUERY)
    private String query = "";
}