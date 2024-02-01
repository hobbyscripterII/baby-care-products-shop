package com.baby.babycareproductsshop.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Data
public class BoardSelVo {
    @Schema(title = "게시글 PK", description = "")
    private int iboard;
    @Schema(title = "회원 PK", description = "")
    private int iuser;
    @Schema(title = "회원 이름", description = "")
    private String nm;
    @Schema(title = "작성일", description = "")
    private String createdAt;
    @Schema(title = "게시글 제목", description = "")
    private String title;
    @Schema(title = "게시글 내용", description = "")
    private String contents;
    @Schema(title = "게시글 사진", description = "")
    private List<String> pics;
    @Schema(title = "댓글", description = "")
    private List<comment> comment;

    @Data
    public static class comment {
        @Schema(title = "댓글 PK", description = "")
        private int icomment;
        @Schema(title = "회원 이름", description = "")
        private String nm;
        @Schema(title = "댓글 내용", description = "")
        private String contents;
        @Schema(title = "작성일", description = "")
        private String createdAt;
    }
}
