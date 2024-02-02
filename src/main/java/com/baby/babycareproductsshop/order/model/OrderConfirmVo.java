package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderConfirmVo {
    @Schema(title = "주문 PK")
    private int iorder;

    @Schema(title = "총 주문 금액")
    private int totalOrderPrice;

    @Schema(title = "주문 상품")
    private List<OrderSelDetailsVo> products;

    @Schema(title = "수령인 이름")
    private String addresseeNm;

    @Schema(title = "수령인 전화번호")
    private String phoneNumber;

    @Schema(title = "수령인 이메일")
    private String email;

    @Schema(title = "수령인 우편번호")
    private String zipCode;

    @Schema(title = "수령인 주소")
    private String address;

    @Schema(title = "수령인 상세주소")
    private String addressDetail;

    @Schema(title = "결제 수단")
    private String paymentOption;

    @Schema(title = "주문일")
    private String createdAt;

    @JsonIgnore
    private String fullAddress;
}
