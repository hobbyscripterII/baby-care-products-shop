package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderGetVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper_ {
    List<OrderGetVo> getOrder(int iorder);
}
