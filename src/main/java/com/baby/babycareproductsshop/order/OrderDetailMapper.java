package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderInsDetailsProcDto;
import com.baby.babycareproductsshop.order.model.OrderInsRefundDto;
import com.baby.babycareproductsshop.order.model.OrderSelDetailsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    int insOrderDetail(OrderInsDetailsProcDto dto);
    List<OrderSelDetailsVo> selOrderDetailsForPurchase(int iorder);
    int updOrderRefundFl(int idetails);
    int insRefund(OrderInsRefundDto dto);
    int updRefund(int idetails);
}
