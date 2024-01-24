package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.ProductSelDto;
import com.baby.babycareproductsshop.review.model.ReviewPicsVo;
import com.baby.babycareproductsshop.review.model.ReviewSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewMapper {
    List<ReviewSelVo> getProductReview (ProductSelDto dto);
    List<ReviewPicsVo> getProductReviewPics(List<Integer> iProduct);
}
