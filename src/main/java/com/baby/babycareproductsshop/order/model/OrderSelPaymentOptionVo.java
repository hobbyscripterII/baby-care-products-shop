package com.baby.babycareproductsshop.order.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderSelPaymentOptionVo {
    @Schema(title = "결제수단 PK")
    private int ipaymentOption;
    @Schema(title = "결제수단")
    private String paymentOption;
}
