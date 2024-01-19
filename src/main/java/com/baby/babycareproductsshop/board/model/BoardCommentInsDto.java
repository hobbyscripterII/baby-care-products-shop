package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardCommentInsDto {
    @Schema(title = "게시판 PK", description = "")
    private int iboard;
    @JsonIgnore
    private int iuser;
    @Schema(title = "댓글 내용", description = "")
    private String comment;
}
