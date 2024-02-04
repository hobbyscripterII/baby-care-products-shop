package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class ProductInsDto {
    @JsonIgnore
    private int iproduct;
    private int imain;
    private int imiddle;
    private String productNm;
    @JsonIgnore
    private String productDetails;
    @Min(1)
    @Max(4)
    private int recommandAge;
    private int price;
    @JsonIgnore
    private String repPic;
    private int remainedCnt;
    @JsonIgnore
    private List<String> pics;
}
