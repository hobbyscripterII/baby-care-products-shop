package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderProductDto;
import com.baby.babycareproductsshop.order.model.OrderSelOrderDetailsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    int insOrderDetail(OrderProductDto dto);
    List<OrderSelOrderDetailsVo> selOrderDetailsForPurchase(int iorder);
}
