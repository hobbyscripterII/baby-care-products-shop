package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.common.OrderCancelAndRefundToStringConverter;
import com.baby.babycareproductsshop.common.ProcessStateCodeToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
public class OrderGetListVo {
    @JsonIgnore
    private int listFl;
    @JsonIgnore
    private int orderFl;
    @Schema(title = "주문 일자", description = "")
    private String createdAt;
    @Schema(title = "주문 PK", description = "")
    private int iorder;
    @Schema(title = "주문 번호", description = "")
    private int idetails;
    @Schema(title = "환불 여부", description = "")
    private int refundFl;
    @Schema(title = "상품 상세 정보", description = "")
    private List<items> items = new ArrayList<>();

    @Data
    public static class items {
        @JsonIgnore
        private int orderFl;
        @JsonIgnore
        private int processStateCode;
        @JsonIgnore
        private int iorder;
        @Schema(title = "주문 PK", description = "")
        private int idetails;
        @Schema(title = "상품 PK", description = "")
        private String iproduct;
        @Schema(title = "상품 대표 사진", description = "")
        private String repPic;
        @Schema(title = "상품 이름", description = "")
        private String productNm;
        @Schema(title = "상품 개수", description = "")
        private int productCnt;
        @Schema(title = "상품 가격", description = "")
        private int price;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Schema(title = "배송 처리 상태", description = "")
        private String processState;
        @Schema(title = "리뷰 작성 여부", description = "")
        private int reviewFl;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        @Schema(title = "주문취소/반품 여부", description = "")
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
}