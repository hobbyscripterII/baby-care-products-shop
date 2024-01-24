package com.baby.babycareproductsshop.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "메인페이지 응답할 전체 리스트 데이터")
public class ProductMainSelVo {

    @Schema(title = "상품 PK")
    private int iproduct;

    @Schema(title = "상품 이름")
    private String productNm;

    @Schema(title = "상품 가격")
    private int price;

    @Schema(title = "추천 상품")
    private int rcFl;

    @Schema(title = "인기 상품")
    private int popFl;

    @Schema(title = "신상품")
    private int newFl;

    @Schema(title = "리뷰 갯수")
    private int reviewCnt;

    @Schema(title = "좋아요 ")
    private int likeProduct;

    @Schema(title = "상품 사진")
    private String repPic;; //상품 사진




}
