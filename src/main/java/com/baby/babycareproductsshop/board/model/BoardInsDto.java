package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardInsDto {
    @JsonIgnore
    private int iboard;
    @JsonIgnore
    private int iuser;
    @Schema(title = "게시판 식별코드", description = "1. 공지사항 2. 소통해요 3. 1:1 문의")
    private int boardCode;
    @Schema(title = "게시글 제목", description = "")
    private String title;
    @Schema(title = "게시글 내용", description = "")
    private String contents;
    @Schema(title = "게시글 사진", description = "")
    private List<MultipartFile> pics;
}