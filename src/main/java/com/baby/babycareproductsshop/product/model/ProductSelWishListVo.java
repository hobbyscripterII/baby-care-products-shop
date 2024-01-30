package com.baby.babycareproductsshop.product.model;

import lombok.Data;

@Data
public class ProductSelWishListVo {
    private int iproduct;
    private String productNm;
    private String price;
    private String repPic;
    private int newFl;
    private int rcFl;
    private int popFl;
    private int reviewCnt;
}
