package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderInsVo {
    private List<UserSelAddressVo> addresses;
    private int iorder;
    private List<OrderSelDetailsVo> products;
    private int totalOrderPrice;
    private List<OrderSelPaymentOptionVo> paymentOptions;
    private String nm;
    private String phoneNumber;
    private String email;
}
