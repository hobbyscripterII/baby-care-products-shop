package com.baby.babycareproductsshop.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
public class UserCheckUidDto {
    @Schema(title = "아이디")
    @NotNull(message = NM_IS_BLANK)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z0-9]{4,10}",
            message = NOT_ALLOWED_ID)
    private String uid;
}
