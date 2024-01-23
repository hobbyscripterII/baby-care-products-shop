package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderProductDto {
    @JsonIgnore
    private int iorder;
    private int iproduct;
    @JsonIgnore
    private int idetails;
    private int productCnt;
    @JsonIgnore
    private int productPrice;
    private int productTotalPrice;
}
