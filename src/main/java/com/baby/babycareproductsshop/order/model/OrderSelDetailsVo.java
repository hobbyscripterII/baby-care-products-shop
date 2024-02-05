package com.baby.babycareproductsshop.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderSelDetailsVo {
    @Schema(title = "상품 pk")
    private int iproduct;
    @Schema(title = "상품 이름")
    private String productNm;
    @Schema(title = "상품 수량")
    private int productCnt;
    @Schema(title = "상품 총 금액")
    private int productTotalPrice;
    @Schema(title = "상품 대표 사진")
    private String repPic;

}
