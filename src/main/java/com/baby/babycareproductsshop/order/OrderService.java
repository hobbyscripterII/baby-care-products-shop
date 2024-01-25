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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final UserAddressMapper addressMapper;
    private final UserMapper userMapper;
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

        UserSelToModifyVo userInfoVo = userMapper.selUserInfoByIuser(dto.getIuser());

        List<OrderSelDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        List<OrderSelPaymentOptionVo> paymentOptions = orderMapper.selPaymentOption();

        return OrderInsVo.builder()
                .addresses(addresses)
                .iorder(dto.getIorder())
                .products(products)
                .totalOrderPrice(dto.getTotalOrderPrice())
                .paymentOptions(paymentOptions)
                .nm(userInfoVo.getNm())
                .phoneNumber(userInfoVo.getPhoneNumber())
                .email(userInfoVo.getEmail())
                .build();

    }

    public OrderConfirmVo confirmOrder(OrderConfirmDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        int updResult = orderMapper.updOrder(dto);

        OrderConfirmVo resultVo = orderMapper.selConfirmOrder(dto);
        List<OrderSelDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelDetailsVo product : products) {
            resultVo.setTotalProductCnt(resultVo.getTotalProductCnt() + product.getProductCnt());
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
            resultVo.setTotalProductCnt(resultVo.getTotalProductCnt() + product.getProductCnt());
        }
        resultVo.setProducts(products);
        return resultVo;
    }

    public ResVo refundOrder(OrderInsRefundDto dto) {
        int updOrderDetailsResult = orderDetailMapper.updOrderRefundFl(dto.getIdetails());
        int insRefundResult = orderDetailMapper.insRefund(dto);
        return new ResVo(Const.SUCCESS);
    }
}
