package com.baby.babycareproductsshop.user.model;

import com.baby.babycareproductsshop.common.Const;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.PASSWORD_IS_BLANK;

@Data
public class UserCheckUpwDto {
    @Schema(title = "비밀번호")
    @NotBlank(message = PASSWORD_IS_BLANK)
    private String upw;
}
