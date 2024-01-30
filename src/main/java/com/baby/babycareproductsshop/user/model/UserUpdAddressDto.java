package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
public class UserUpdAddressDto {
    @JsonIgnore
    private int iuser;

    @Schema(title = "주소 pk")
    @Positive
    private int iaddress;

    @Schema(title = "우편 번호")
    @NotBlank(message = ZIP_CODE_IS_BLANK)
    private String zipCode;

    @Schema(title = "주소")
    @NotBlank(message = ADDRESS_IS_BLANK)
    private String address;

    @Schema(title = "상세 주소")
    @NotBlank(message = ADDRESS_DETAIL_IS_BLANK)
    private String addressDetail;
}
