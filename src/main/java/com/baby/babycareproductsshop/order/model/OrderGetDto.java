package com.baby.babycareproductsshop.order.model;

import com.baby.babycareproductsshop.common.ProcessStateCodeToStringConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderGetDto {
    @Schema(title = "주문 PK", description = "")
    private int iorder;
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private int listFl;
}