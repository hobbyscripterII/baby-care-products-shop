package com.baby.babycareproductsshop.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserChildVo {
    @Schema(title = "자녀 PK")
    private int ichild;
    @Schema(title = "자녀 나이대 PK")
    private int ichildAge;
    @Schema(title = "자녀 성별")
    private String gender;
}
