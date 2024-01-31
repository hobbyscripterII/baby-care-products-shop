package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService_ {
    private final OrderMapper_ orderMapper;
    private final AuthenticationFacade authenticationFacade;

    public List<OrderGetListVo> getOrder(OrderGetListDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<OrderGetListVo> orderList = orderMapper.getOrderList(dto);
        List<OrderGetListVo.items> productList = orderMapper.getProductList(dto);

        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < productList.size(); j++) {
                if (orderList.get(i).getIorder() == productList.get(j).getIorder()) {
                    orderList.get(i).getItems().add(productList.get(j));
                }

//                if(dto.getListFl() == 2) {
//
//                }
            }
        }

        return orderList;
    }

    public ResVo orderCancel(int iorder) {
        int orderCancelRows = orderMapper.orderCancel(iorder); // 추후 예외처리
        return new ResVo(orderCancelRows);
    }
}