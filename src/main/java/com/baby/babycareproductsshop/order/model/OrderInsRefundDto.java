package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderInsRefundDto {
    @JsonIgnore
    private int idetails;
    private String contents;
    private int refundCnt;
    private int refundPrice;
}
