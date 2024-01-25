package com.baby.babycareproductsshop.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {
    private int iorder;
    private int totalProductCnt;
    private List<OrderSelDetailsVo> products;
    private String addresseeNm;
    private String phoneNumber;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String paymentOption;
    private String createdAt;
}
