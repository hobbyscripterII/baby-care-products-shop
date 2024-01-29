package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService_ {
    private final OrderMapper orderMapper;
    private final AuthenticationFacade authenticationFacade;

    public List<OrderGetListVo> getOrder(OrderGetListDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<OrderGetListVo> list = orderMapper.getOrder(dto);
        return list;
    }

    public ResVo orderCancel(int iorder) {
        int orderCancelRows = orderMapper.orderCancel(iorder); // 추후 예외처리
        return new ResVo(orderCancelRows);
    }
}
