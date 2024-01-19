package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDelDto {
    @Schema(title = "게시글 PK", description = "")
    private int iboard;
    @JsonIgnore
//    @Schema(title = "회원 PK", description = "")
    private int iuser;
}