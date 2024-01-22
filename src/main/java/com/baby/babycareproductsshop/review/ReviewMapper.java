package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.review.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    // 리뷰 작성
    int insReview(ReviewInsDto dto);
    int insReviewPics(ReviewPicsInsDto insDto);
    // 리뷰 목록
    List<ReviewSelVo> getReview(ReviewSelDto dto);
    List<ReviewPicsVo> getReviewPics(List<Integer> ireview);
    // 리뷰 삭제
    int selReviewByReview(ReviewDelDto dto);
    int delReviewByPics(ReviewDelDto dto);
    int delReview(ReviewDelDto dto);
}
