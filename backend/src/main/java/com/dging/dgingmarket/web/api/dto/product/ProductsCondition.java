package com.dging.dgingmarket.web.api.dto.product;

import com.dging.dgingmarket.domain.type.MainCategory;
import com.dging.dgingmarket.util.constant.ValidationMessages;
import com.dging.dgingmarket.util.validation.Enum;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.REQUEST_MAIN_CATEGORY;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductsCondition {

    @Parameter
    @Enum(enumClass = MainCategory.class, ignoreCase = true, message = ValidationMessages.MAIN_CATEGORY)
    @Schema(description = REQUEST_MAIN_CATEGORY)
    private String mainCategory;

    public MainCategory getMainCategory() {
        return MainCategory.find(mainCategory);
    }

}
