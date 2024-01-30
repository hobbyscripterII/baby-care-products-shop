package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderGetListDto {
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private int listFl;
    @Schema(title = "주문 처리 상태", description = "1 - 입금전 2 - 배송준비중 3 - 배송중 4 - 배송완료 5 - 환불/취소")
    private int processStateCode;
    @Schema(title = "주문 PK", description = "")
    private int date;
}