package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class OrderInsDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "상품 정보")
    List<@Valid OrderInsDetailsProcDto> products;
    @Schema(title = "총 주문 금액")
    private int totalOrderPrice;
    @JsonIgnore
    private int iaddress;
    @JsonIgnore
    private int iorder;
}
