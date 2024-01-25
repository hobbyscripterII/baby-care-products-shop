package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSignInVo {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String nm;
    private int result;
    private String accessToken;
}
