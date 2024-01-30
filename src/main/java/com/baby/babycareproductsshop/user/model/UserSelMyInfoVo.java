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
    @Schema(title = "이름")
    private String nm;
    @Schema(title = "입금 전")
    private int beforeDeposit;
    @Schema(title = "배송 준비 중")
    private int preparation;
    @Schema(title = "배송중")
    private int shipping;
    @Schema(title = "배송 완료")
    private int completed;
    @Schema(title = "찜 목록")
    private List<ProductSelWishListVo> myWishList;
}
