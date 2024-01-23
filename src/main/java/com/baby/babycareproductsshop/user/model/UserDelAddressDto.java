package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserDelAddressDto {
    @JsonIgnore
    private int iuser;
    @Positive
    private int iaddress;
}
