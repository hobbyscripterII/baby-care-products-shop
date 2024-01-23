package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderInsDto;
import com.baby.babycareproductsshop.order.model.OrderProductDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int insOrder(OrderInsDto dto);
    int insOrderDetail(OrderProductDto dto);
}
