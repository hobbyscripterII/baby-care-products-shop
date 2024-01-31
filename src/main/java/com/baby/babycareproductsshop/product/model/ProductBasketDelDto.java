package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ProductBasketDelDto {
    private List<Integer> iproduct;
    @JsonIgnore
    private int iuser;
}
