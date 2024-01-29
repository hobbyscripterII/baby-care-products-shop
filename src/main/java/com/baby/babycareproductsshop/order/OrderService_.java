package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.OrderGetDto;
import com.baby.babycareproductsshop.order.model.OrderGetListVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService_ {
    private final OrderMapper_ mapper;
    private final AuthenticationFacade authenticationFacade;

    public List<OrderGetListVo> getOrder(OrderGetDto dto) {
        List<OrderGetListVo> list = mapper.getOrder(dto);

        return list;
    }

    public ResVo orderCancel(int iorder) {
        int orderCancelRows = mapper.orderCancel(iorder); // 추후 예외처리
        return new ResVo(orderCancelRows);
    }
}
