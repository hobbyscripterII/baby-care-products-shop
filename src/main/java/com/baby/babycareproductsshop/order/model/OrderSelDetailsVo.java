package com.baby.babycareproductsshop.order.model;

import lombok.Data;

@Data
public class OrderSelDetailsVo {
    private String productNm;
    private int productCnt;
    private String productTotalPrice;
    private String repPic;

}
