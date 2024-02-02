package com.baby.babycareproductsshop.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardUpdDto {
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private List<MultipartFile> pics;
    @Schema(title = "게시글 PK", description = "")
    private int iboard;
    @Schema(title = "게시판 카테고리", description = "")
    private int boardCode;
    @Schema(title = "게시글 제목", description = "")
    private String title;
    @Schema(title = "게시글 내용", description = "")
    private String contents;
}