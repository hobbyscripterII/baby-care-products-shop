package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.user.UserAddressMapper;
import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final UserAddressMapper addressMapper;
    private final AuthenticationFacade authenticationFacade;

    public OrderInsVo postOrder(OrderInsDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<UserSelAddressVo> addresses = addressMapper.selUserAddress(dto.getIuser());
        dto.setIaddress(addresses.get(0).getIaddress());
        int insOrderResult = orderMapper.insOrder(dto);
        for (OrderInsDetailsProcDto product : dto.getProducts()) {
            product.setIorder(dto.getIorder());
            product.setProductPrice(product.getProductTotalPrice() / product.getProductCnt());
            int insOrderDetails = orderDetailMapper.insOrderDetail(product);
        }

        OrderInsVo result = new OrderInsVo();
        result.setIorder(dto.getIorder());
        result.setTotalOrderPrice(dto.getTotalOrderPrice());
        result.setAddresses(addresses);
        List<OrderSelOrderDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        result.setProducts(products);
        List<OrderSelPaymentOptionVo> paymentOptions = orderMapper.selPaymentOption();
        result.setPaymentOptions(paymentOptions);

        return result;
    }

    public OrderConfirmOrderVo confirmOrder(OrderConfirmOrderDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        int updResult = orderMapper.updOrder(dto);

        OrderConfirmOrderVo resultVo = orderMapper.selConfirmOrder(dto);
        List<OrderSelOrderDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelOrderDetailsVo product : products) {
            resultVo.setTotalProductCnt(resultVo.getTotalProductCnt() + product.getProductCnt());
        }
        resultVo.setProducts(products);
        return resultVo;
    }

    public OrderConfirmOrderVo getOrderDetail(int iorder) {
        OrderConfirmOrderDto dto = new OrderConfirmOrderDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());
        dto.setIorder(iorder);

        OrderConfirmOrderVo resultVo = orderMapper.selConfirmOrder(dto);
        List<OrderSelOrderDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelOrderDetailsVo product : products) {
            resultVo.setTotalProductCnt(resultVo.getTotalProductCnt() + product.getProductCnt());
        }
        resultVo.setProducts(products);
        return resultVo;
    }
}
