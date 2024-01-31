package com.baby.babycareproductsshop.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserSignInProcDto {
    private int iuser;
    private String upw;
    private String nm;
    @JsonIgnore
    private int unregisterFl;
}
