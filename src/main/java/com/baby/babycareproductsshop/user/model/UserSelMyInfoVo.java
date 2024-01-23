package com.baby.babycareproductsshop.user.model;

import com.baby.babycareproductsshop.product.model.ProductSelWishListVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserSelMyInfoVo {

    private String nm;
    private int beforeDeposit;
    private int preparation;
    private int shipping;
    private int completed;
    private List<ProductSelWishListVo> myWishList;
}
