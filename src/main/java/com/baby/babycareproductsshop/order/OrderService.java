package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.user.UserAddressMapper;
import com.baby.babycareproductsshop.user.UserMapper;
import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
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
        OrderInsVo result = orderMapper.selOrderForConfirm(iorder);
//        result.setAddresses(addressMapper.selUserAddress(iuser));
        result.setProducts(orderDetailMapper.selOrderDetailsForPurchase(iorder));
        result.setPaymentOptions(orderMapper.selPaymentOption());
        return result;
    }

    @Transactional
    public ResVo putConfirmOrder(OrderConfirmDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        if (dto.getIpaymentOption() == 2) {
            dto.setProcessState(1);
        }
        dto.setProcessState(2);
        List<UserSelAddressVo> addresses = addressMapper.selUserAddress(dto.getIuser());
        for (UserSelAddressVo address : addresses) {
            if (address.getIaddress() == dto.getIaddress()){
                StringBuilder sb = new StringBuilder();
                sb.append(address.getZipCode()).append("_").append(address.getAddress()).append("_").append(address.getAddressDetail());
                dto.setFullAddress(sb.toString());
            }
        }
        int updResult = orderMapper.updOrder(dto);

        return new ResVo(Const.SUCCESS);
    }

    public OrderConfirmVo getOrderDetails(int iorder) {
        OrderConfirmDto dto = new OrderConfirmDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());
        dto.setIorder(iorder);

        OrderConfirmVo resultVo = orderMapper.selConfirmOrder(dto);
        String[] fullAddress =resultVo.getFullAddress().split("_");
        resultVo.setZipCode(fullAddress[0]);
        resultVo.setAddress(fullAddress[1]);
        resultVo.setAddressDetail(fullAddress[2]);

        List<OrderSelDetailsVo> products = orderDetailMapper.selOrderDetailsForPurchase(dto.getIorder());
        for (OrderSelDetailsVo product : products) {
            resultVo.setTotalOrderPrice(resultVo.getTotalOrderPrice() + product.getProductTotalPrice());
        }
        resultVo.setProducts(products);
        return resultVo;
    }

    @Transactional
    public ResVo refundOrder(OrderInsRefundDto dto) {
        int updOrderDetailsResult = orderDetailMapper.updOrderRefundFl(dto.getIdetails());
        int insRefundResult = orderDetailMapper.insRefund(dto);
        return new ResVo(Const.SUCCESS);
    }

    public List<OrderGetListVo> getOrder(OrderGetListDto dto) {
        dto.setIuser(authenticationFacade.getLoginUserPk());
        List<OrderGetListVo> orderList = orderMapper.getOrderList(dto);
        List<OrderGetListVo.items> productList = orderMapper.getProductList(dto);

        for (int i = 0; i < orderList.size(); i++) {
            for (int j = 0; j < productList.size(); j++) {
                if (orderList.get(i).getIorder() == productList.get(j).getIorder()) {
                    orderList.get(i).getItems().add(productList.get(j));
                }
            }
        }
        return orderList;
    }

    public ResVo orderCancel(int iorder) {
        int orderCancelRows = orderMapper.orderCancel(iorder); // 추후 예외처리
        return new ResVo(orderCancelRows);
    }
}
