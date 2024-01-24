package com.baby.babycareproductsshop.order.model;

import lombok.Data;

@Data
public class OrderConfirmDetailsProcVo {
    private String productNm;
    private int productCnt;
    private int productTotalPrice;
    private String repPic;
}
