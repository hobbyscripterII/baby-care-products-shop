package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "상품 상세 정보 필요한 데이터")
public class ProductSelDto {

    @JsonIgnore
    @Schema(title = "상품 PK")
    private int iproduct;

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 5;

    @Schema(title = "페이징처리")
    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
    }


}
