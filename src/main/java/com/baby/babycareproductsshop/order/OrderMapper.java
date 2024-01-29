package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int insOrder(OrderInsDto dto);
    List<OrderSelPaymentOptionVo> selPaymentOption();
    int updOrder(OrderConfirmDto dto);
    OrderConfirmVo selConfirmOrder(OrderConfirmDto dto);
    int updRefundFl(int idetails);

    List<OrderGetListVo> getOrder(OrderGetListDto dto);
    int orderCancel(int iorder);
}
