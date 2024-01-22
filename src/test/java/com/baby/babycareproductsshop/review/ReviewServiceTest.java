package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.review.model.ReviewInsDto;
import com.baby.babycareproductsshop.review.model.ReviewPicsInsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({ReviewService.class})
class ReviewServiceTest {

    @MockBean ReviewMapper mapper;

    @Autowired ReviewService service;

    @Test
    void insReview() {

        when(mapper.insReview(any())).thenReturn(1);
        when(mapper.insReviewPics(any())).thenReturn(1);

        ReviewInsDto dto = new ReviewInsDto();
        dto.setIreview(1);
        ResVo vo = service.insReview(dto);
        assertEquals(dto.getIreview(), vo.getResult());

        verify(mapper).insReview(any());
        verify(mapper).insReviewPics(any());

        ReviewPicsInsDto insDto = new ReviewPicsInsDto();
        insDto.setIreview(dto.getIreview());
    }

    @Test
    void getReview() {
    }

    @Test
    void delReview() {
    }
}