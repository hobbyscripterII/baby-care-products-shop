package com.baby.babycareproductsshop.board.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardGetVo {
    @Schema(title = "게시글 PK", description = "")
    private int iboard;
    @Schema(title = "게시글 제목", description = "")
    private String title;
    @Schema(title = "게시판 식별코드", description = "1. 공지사항 2. 소통해요 3. 1:1 문의")
    private int boardCode;
    @Schema(title = "게시글 작성일", description = "")
    private String createdAt;
}