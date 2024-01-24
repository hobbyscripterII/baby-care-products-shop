package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class OrderInsDto {
    @JsonIgnore
    private int iuser;
    List<OrderProductDto> products;
    private int totalOrderPrice;
    @JsonIgnore
    private int iaddress;
    @JsonIgnore
    private int iorder;
}
