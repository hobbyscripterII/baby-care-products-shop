package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insOrder(OrderInsDto dto);
    List<OrderSelPaymentOptionVo> selPaymentOption();
    OrderInsVo selInsOrderResult(int iorder);
    int updOrder(OrderConfirmOrderDto dto);
    OrderConfirmOrderVo selConfirmOrder(int iorder);
}
