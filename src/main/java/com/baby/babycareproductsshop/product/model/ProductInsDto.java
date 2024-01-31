package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class ProductInsDto {
    @JsonIgnore
    private int iproduct;
    private int imain;
    private int imiddle;
    private int productNm;
    private int recommandAge;
    private int price;
    private int repPic;
    private int remainedProduct;
    private List<String> pics;
}
