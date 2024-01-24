package com.baby.babycareproductsshop.order.model;

import lombok.Data;

@Data
public class OrderSelOrderDetailsVo {
    private String productNm;
    private int productCnt;
    private String productTotalPrice;
    private String repPic;

}
