package com.baby.babycareproductsshop.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ProcessState {
    BUY_CHECK(0, "구매확인"),
    DELIVER_PREPARE(1, "배송준비중"),
    DELIVER_IN_PROGRESS(2, "배송중"),
    DELIVER_SUCCESS(3, "배송완료"),
    ORDER_CANCEL(4, "주문취소"),
    RETURN(5, "반품");

    private final int processStateNum;
    private final String processState;
}
