package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderConfirmDto {
    @JsonIgnore
    private int iuser;
    private int iorder;
    private int iaddress;
    private String addresseeNm;
    private String email;
    private String phoneNumber;
    private int ipaymentOption;
    @JsonIgnore
    private int processState;
}
