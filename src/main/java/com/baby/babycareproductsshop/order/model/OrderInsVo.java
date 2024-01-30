package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class OrderInsVo {
    @Schema(title = "주문 PK")
    private int iorder;
    @Schema(title = "주소 목록")
    private List<UserSelAddressVo> addresses;
    @Schema(title = "수령인 이름")
    private String addresseeNm;
    @Schema(title = "수령인 전화번호")
    private String phoneNumber;
    @Schema(title = "수령인 이메일")
    private String email;
    @Schema(title = "구매할 물품 목록")
    private List<OrderSelDetailsVo> products;
    @Schema(title = "총 주문 상품 수")
    private int totalProductCnt;
    @Schema(title = "총 주문 상품 금액")
    private int totalOrderPrice;
    @Schema(title = "결제 수단")
    private List<OrderSelPaymentOptionVo> paymentOptions;
}
