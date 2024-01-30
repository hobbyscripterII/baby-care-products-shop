package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class UserSelToModifyVo {
    @JsonIgnore
    private String upw;
    @Schema(title = "결과값")
    private int result;
    @Schema(title = "이름")
    private String nm;
    @Schema(title = "전화번호")
    private String phoneNumber;
    @Schema(title = "이메일")
    private String email;
    @Schema(title = "자녀 정보")
    private List<UserChildVo> children;
}
