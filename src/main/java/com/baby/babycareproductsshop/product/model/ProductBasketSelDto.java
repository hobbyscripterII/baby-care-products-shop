package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ProductBasketSelDto {
    @JsonIgnore
    private int iuser;
}
