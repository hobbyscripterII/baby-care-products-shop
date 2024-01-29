package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.common.OrderCancelAndRefundToStringConverter;
import com.baby.babycareproductsshop.common.ProcessStateCodeToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderGetListVo {
    @JsonIgnore
    private Integer processStateCode;
    @Schema(title = "주문 PK", description = "")
    private int iorder;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "배송 처리 상태", description = "") // 주문 취소/반품 페이지에선 필요 x
    private String processState;
    @Schema(title = "상품 PK", description = "")
    private int iproduct;
    @Schema(title = "주문 일자", description = "")
    private String createdAt;
    @Schema(title = "주문 번호", description = "")
    private int idetails;
    @Schema(title = "상품 이름", description = "")
    private String productNm;
    @Schema(title = "상품 수량", description = "")
    private int productCnt;
    @Schema(title = "상품별 총 금액", description = "")
    private int price;
    @Schema(title = "환불 여부", description = "")
    private int refundFl;
    @Schema(title = "리뷰 작성 여부", description = "")
    private int reviewFl;
    @JsonIgnore
    private int orderFl;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(title = "주문 취소/반품 여부", description = "")
    private String orderCancelAndRefund;

    public String getProcessState() {
        if (orderFl == 1) {
            return null;
        }
        return new ProcessStateCodeToStringConverter().convert(processStateCode);
    }

    public String getOrderCancelAndRefund() {
        return new OrderCancelAndRefundToStringConverter().convert(orderFl);
    }
}