package com.baby.babycareproductsshop.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserSelAddressVo {
    @Schema(title = "주소 PK")
    private int iaddress;
    @Schema(title = "우편 번호")
    private String zipCode;
    @Schema(title = "주소")
    private String address;
    @Schema(title = "상세 주소")
    private String addressDetail;
}
