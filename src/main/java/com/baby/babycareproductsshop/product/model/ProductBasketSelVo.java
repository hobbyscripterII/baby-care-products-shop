package com.baby.babycareproductsshop.product.model;

import com.baby.babycareproductsshop.review.model.ReviewSelVo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(title = "장바구니 상세 정보")
public class ProductBasketSelVo {

    @JsonIgnore
    @Schema(title = "상품 PK")
    private int iproduct;

    @Schema(title = "상품 이름")
    private String productNm;

    @Schema(title = "상품 가격")
    private int price;

    @Schema(title = "상품 수량")
    private int productCnt;

    @Schema(title = "상품 총 가격")
    private int totalPrice;

    @Schema(title = "최종 결제 가격")
    private int paymentAmount;

    @Schema(title = "사진")
    private String repPic;




}

