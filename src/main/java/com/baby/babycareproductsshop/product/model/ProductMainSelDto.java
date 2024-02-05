package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "로그인 시 필요한 데이터")
public class ProductMainSelDto {

    @JsonIgnore
    @Schema(title = "상품PK")
    private int iproduct;

    @JsonIgnore
    @Schema(title = "유저자녀나이")
    private List<Integer> recommandAge;

    @Schema(title = "유저 PK")
    @JsonIgnore
    private int iuser;

}
