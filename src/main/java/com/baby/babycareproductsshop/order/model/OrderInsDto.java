package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OrderInsDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "상품 정보")
    @Size(min = 1, message = "상품을 하나 이상 선택해주세요.")
    List<@Valid OrderInsDetailsProcDto> products;
    @Schema(title = "총 주문 금액")
    @Positive
    private int totalOrderPrice;
    @JsonIgnore
    private int iaddress;
    @JsonIgnore
    private int iorder;
}
