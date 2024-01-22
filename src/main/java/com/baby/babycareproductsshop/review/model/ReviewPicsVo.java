package com.baby.babycareproductsshop.review.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPicsVo {
    @Schema(title = "리뷰 PK")
    private int ireview;
    @Schema(title = "리뷰의 사진정보")
    private String reviewPic;
}
