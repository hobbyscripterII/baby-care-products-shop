package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardCommentUpdDto {
    @JsonIgnore
    private int iuser;
    @Schema(title = "댓글 PK", description = "")
    private int icomment;
    @Schema(title = "댓글 내용", description = "")
    private String comment;
}
