package com.baby.babycareproductsshop.review.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSelVo{
    @Schema(title = "리뷰 PK")
    private int ireview;
    @Schema(title = "리뷰 작성한 사람의 이름")
    private String nm;

    @Schema(title = "리뷰 평점")
    private int productScore;
    @Schema(title = "리뷰 내용")
    private String contents;

    private String reqReviewPic;

    @Schema(title = "리뷰를 작성한 시간")
    private String createdAt;
    //
    @Schema(title = "리뷰에 등록한 사진")
    private List<String> pics = new ArrayList<>();
}
