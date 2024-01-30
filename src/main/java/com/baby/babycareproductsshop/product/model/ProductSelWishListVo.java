package com.baby.babycareproductsshop.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProductSelWishListVo {
    @Schema(title = "상품 pk")
    private int iproduct;
    @Schema(title = "상품 이름")
    private String productNm;
    @Schema(title = "상품 가격")
    private String price;
    @Schema(title = "상품 대표 사진")
    private String repPic;
    @Schema(title = "신상품 구분값", description = " 1 : 신상품 ")
    private int newFl;
    @Schema(title = "추천 상품 구분값", description = " 1 : 추천 상품 ")
    private int rcFl;
    @Schema(title = "인기 상품 구분값", description = " 1 : 인기 상품 ")
    private int popFl;
    @Schema(title = "리뷰 갯수")
    private int reviewCnt;
}
