package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderGetDto;
import com.baby.babycareproductsshop.order.model.OrderGetListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper_ {
    List<OrderGetListVo> getOrder(OrderGetDto dto);
    int orderCancel(int iorder);
}
