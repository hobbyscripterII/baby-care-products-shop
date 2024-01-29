package com.baby.babycareproductsshop.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {
    private int iorder;
    private int totalOrderPrice;
    private List<OrderSelDetailsVo> products;
    private String addresseeNm;
    private String phoneNumber;
    private String email;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String paymentOption;
    private String createdAt;
}
