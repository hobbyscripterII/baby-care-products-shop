package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductRegistrationDto {
    @JsonIgnore
    private int iproduct;


    private int imain;
    private int imiddle;
    private String productNm;
    private MultipartFile productDetails;
    private int recommandAge;
    private int price;
    private String repPic;
    private int remainedProduct;


}
