package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderInsRefundDto {
    @JsonIgnore
    private int idetails;
    @Schema(title = "환불 사유")
    @NotBlank(message = "환불 사유를 입력해주세요.")
    private String contents;
    @Schema(title = "환불 수량")
    @Positive
    private int refundCnt;
    @Schema(title = "환불 금액")
    @Positive
    private int refundPrice;
}
