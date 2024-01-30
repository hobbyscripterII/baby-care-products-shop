package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.user.UserAddressMapper;
import com.baby.babycareproductsshop.user.UserMapper;
import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import com.baby.babycareproductsshop.user.model.UserSelToModifyVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final UserAddressMapper addressMapper;
    private final UserMapper userMapper;
    private final AuthenticationFacade authenticationFacade;

    @Transactional
    public ResVo postOrder(OrderInsDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<UserSelAddressVo> addresses = addressMapper.selUserAddress(dto.getIuser());
        dto.setIaddress(addresses.get(0).getIaddress());
        int insOrderResult = orderMapper.insOrder(dto);

        for (OrderInsDetailsProcDto product : dto.getProducts()) {
            product.setIorder(dto.getIorder());
            product.setProductPrice(product.getProductTotalPrice() / product.getProductCnt());
            int insOrderDetails = orderDetailMapper.insOrderDetail(product);
        }

        return new ResVo(dto.getIorder());
    }

    public OrderInsVo getOrderForConfirm(int iorder) {
        int iuser = authenticationFacade.getLoginUserPk();
        OrderInsVo result = orderMapper.selOrderForConfirm(iorder);
        result.setAddresses(addressMapper.selUserAddress(iuser));
        result.setProducts(orderDetailMapper.selOrderDetailsForPurchase(iorder));
        result.setPaymentOptions(orderMapper.selPaymentOption());
        return result;
    }

    @Transactional
    public OrderConfirmVo putConfirmOrder(OrderConfirmDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        if (dto.getIpaymentOption() == 2) {
            dto.setProcessState(1);
        }
        dto.setProcessState(2);
        int updResult = orderMapper.updOrder(dto);

        OrderConfirmVo resultVo = orderMapper.selConfirmOrder(dto);
        List<OrderSelDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelDetailsVo product : products) {
            resultVo.setTotalOrderPrice(resultVo.getTotalOrderPrice() + product.getProductTotalPrice());
        }
        resultVo.setProducts(products);
        return resultVo;
    }

    public OrderConfirmVo getOrderDetails(int iorder) {
        OrderConfirmDto dto = new OrderConfirmDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());
        dto.setIorder(iorder);

        OrderConfirmVo resultVo = orderMapper.selConfirmOrder(dto);
        List<OrderSelDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelDetailsVo product : products) {
            resultVo.setTotalOrderPrice(resultVo.getTotalOrderPrice() + product.getProductTotalPrice());
        }
        resultVo.setProducts(products);
        return resultVo;
    }

    public ResVo refundOrder(OrderInsRefundDto dto) {
        int updOrderDetailsResult = orderDetailMapper.updOrderRefundFl(dto.getIdetails());
        int insRefundResult = orderDetailMapper.insRefund(dto);
        return new ResVo(Const.SUCCESS);
    }

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
