package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderInsRefundDto {
    @JsonIgnore
    private int idetails;
    @Schema(title = "환불 사유")
    @NotNull
    private String contents;
    @Schema(title = "환불 수량")
    @Positive
    private int refundCnt;
    @Schema(title = "환불 금액")
    @Positive
    private int refundPrice;
}
