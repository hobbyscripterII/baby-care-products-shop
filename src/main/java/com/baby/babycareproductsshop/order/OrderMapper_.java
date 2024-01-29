package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper_ {
    List<OrderGetListVo> getOrder(OrderGetListDto dto);
    int orderCancel(int iorder);
}
