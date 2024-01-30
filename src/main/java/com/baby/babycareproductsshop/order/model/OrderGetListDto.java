package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderGetListDto {
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private int listFl;
}