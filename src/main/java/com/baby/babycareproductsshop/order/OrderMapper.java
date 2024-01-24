package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderInsDto;
import com.baby.babycareproductsshop.order.model.OrderInsVo;
import com.baby.babycareproductsshop.order.model.OrderSelPaymentOptionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insOrder(OrderInsDto dto);
    List<OrderSelPaymentOptionVo> selPaymentOption();
    OrderInsVo selInsOrderResult(int iorder);
}
