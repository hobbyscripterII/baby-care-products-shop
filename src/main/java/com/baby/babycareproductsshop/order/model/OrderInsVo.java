package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderInsVo {
    private List<UserSelAddressVo> addresses;
    private int iorder;
    private List<OrderSelOrderDetailsVo> products;
    private int totalOrderPrice;
    private List<OrderSelPaymentOptionVo> paymentOptions;
}
