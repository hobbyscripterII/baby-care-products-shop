package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderGetDto;
import com.baby.babycareproductsshop.order.model.OrderGetListVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
