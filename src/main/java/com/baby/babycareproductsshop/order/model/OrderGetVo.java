package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.common.ProcessStateCodeToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.DefaultConversionService;

@Slf4j
@Data
public class OrderGetVo {
    @JsonIgnore
    private Integer processStateCode;
    @Schema(title = "배송 처리 상태", description = "")
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

    public int getPrice() {
        return this.price * productCnt;
    }

    public String getProcessState() {
        return new ProcessStateCodeToStringConverter().convert(processStateCode);
    }
}