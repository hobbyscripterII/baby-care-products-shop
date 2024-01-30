package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserDelAddressDto {
    @JsonIgnore
    private int iuser;
    @Positive
    @Schema(title = "주소 pk")
    private int iaddress;
}
