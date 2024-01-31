package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderInsDetailsProcDto {
    @JsonIgnore
    private int iorder;
    @Schema(title = "상품 PK")
    @Positive
    private int iproduct;
    @Schema(title = "상품 수량")
    @Positive
    private int productCnt;
    @JsonIgnore
    private int productPrice;
    @Schema(title = "총 상품 금액")
    @Positive
    private int productTotalPrice;
}
