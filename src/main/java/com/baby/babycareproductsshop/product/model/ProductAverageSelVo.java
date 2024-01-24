package com.baby.babycareproductsshop.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(title = "리뷰 수 와 평균 별점 조회")
public class ProductAverageSelVo {

    @Schema(title = "리뷰 갯수")
    private int reviewCnt; // 리뷰 갯수

    @Schema(title = "평균 별점")
    private double avgProductScore; //평균 별점

    @Schema(title = "평균 별점 세팅")
    public void setAvgProductScore(double avgProductScore) {
        this.avgProductScore =  Math.round(avgProductScore * 10.0) / 10.0;

    }
}
