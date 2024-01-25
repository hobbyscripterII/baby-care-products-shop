package com.baby.babycareproductsshop.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.NM_IS_BLANK;
import static com.baby.babycareproductsshop.common.Const.PASSWORD_IS_BLANK;

@Data
public class UserSignInDto {
    @Schema(defaultValue = "hubble")
    @NotBlank(message = NM_IS_BLANK)
    private String uid;
    @Schema(defaultValue = "xptmxm12!@")
    @NotBlank(message = PASSWORD_IS_BLANK)
    private String upw;
}
