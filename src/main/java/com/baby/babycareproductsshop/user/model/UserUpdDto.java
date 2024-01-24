package com.baby.babycareproductsshop.user.model;

import com.baby.babycareproductsshop.common.Const;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static com.baby.babycareproductsshop.common.Const.*;

@Data
public class UserUpdDto {
    @JsonIgnore
    private int iuser;

    @Schema(description = "유저 비밀번호", example = "특수문자는 @$!%*?&#~_-를 사용할 수 있습니다.")
    @NotNull(message = PASSWORD_IS_BLANK)
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$",
            message = NOT_ALLOWED_ID)
    private String upw;


    @NotBlank(message = NM_IS_BLANK)
    private String nm;

    @NotNull(message = PHONE_NUMBER_IS_BLANK)
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}",
            message = NOT_ALLOWED_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull(message = EMAIL_IS_BLANK)
    @Pattern(regexp = "\\w+@\\w{3,}\\.([a-zA-Z]{2,}|[a-zA-Z]{2,}\\.[a-zA-Z]{2,})",
            message = NOT_ALLOWED_EMAIL)
    private String email;

    @Size(min = 1, max = 3, message = NOT_ALLOWED_CHILDREN_COUNT)
    private List<@Valid UserChildDto> children;
}
