package com.baby.babycareproductsshop.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmOrderVo {
    private int iorder;
    private int totalProductCnt;
    private List<OrderConfirmDetailsProcVo> products;
    private String addresseeNm;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String paymentOption;
    private String createdAt;
}
