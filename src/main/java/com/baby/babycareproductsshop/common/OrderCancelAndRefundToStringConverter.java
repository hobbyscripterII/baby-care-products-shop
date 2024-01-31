package com.baby.babycareproductsshop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class OrderCancelAndRefundToStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(Integer source) {
        String orderCancelAndRefund = null;

        switch (source) {
            case 5:
                orderCancelAndRefund = ProcessState.ORDER_CANCEL.getProcessState();
                break;
            case 6:
                orderCancelAndRefund = ProcessState.RETURN.getProcessState();
                break;
        }
        return orderCancelAndRefund;
    }
}