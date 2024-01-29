package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ProcessState;
import com.baby.babycareproductsshop.order.model.OrderGetVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService_ {
    private final OrderMapper_ mapper;
    private final AuthenticationFacade authenticationFacade;

    public List<OrderGetVo> getOrder(int iorder) {
        List<OrderGetVo> list = mapper.getOrder(iorder);
        return list;
    }
}
