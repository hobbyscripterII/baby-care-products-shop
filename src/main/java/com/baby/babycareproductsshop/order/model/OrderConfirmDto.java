package com.baby.babycareproductsshop.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
public class OrderConfirmDto {
    @JsonIgnore
    private int iuser;

    @Schema(title = "주문 PK")
    private int iorder;

    @Schema(title = "주소 PK")
    private int iaddress;

    @Schema(title = "수령인 이름")
    @NotBlank(message = NM_IS_BLANK)
    private String addresseeNm;

    @Schema(title = "수령인 전화번호")
    @NotNull(message = PHONE_NUMBER_IS_BLANK)
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}",
            message = NOT_ALLOWED_PHONE_NUMBER)
    private String phoneNumber;

    @Schema(title = "수령인 이메일")
    @NotNull(message = EMAIL_IS_BLANK)
    @Pattern(regexp = "\\w+@\\w{3,}\\.([a-zA-Z]{2,}|[a-zA-Z]{2,}\\.[a-zA-Z]{2,})",
            message = NOT_ALLOWED_EMAIL)
    private String email;

    @Schema(title = "결제수단 PK")
    private int ipaymentOption;

    @JsonIgnore
    private int processState;

    @JsonIgnore
    private String fullAddress;
}
