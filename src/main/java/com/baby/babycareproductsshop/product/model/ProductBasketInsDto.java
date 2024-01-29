package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(title = "장바구니 넣을 때 필요한 데이터")
public class ProductBasketInsDto {

    @Schema(title = "유저 PK 안쓸꺼")
    @JsonIgnore
    private int iuser;

    @Schema(title = "상품 PK")
    private int iproduct;


    @Schema(title = "수량",defaultValue = "1")
    private int productCnt;
}
