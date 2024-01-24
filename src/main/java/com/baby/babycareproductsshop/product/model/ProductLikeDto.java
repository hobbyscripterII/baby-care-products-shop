package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "찜하기 필요한 데이터")
public class ProductLikeDto {

    @Schema(title = "유저 PK")
    @JsonIgnore
    private int iuser;

    @Schema(title = "상품 PK")
    private int iproduct;
}

