package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.review.model.ReviewDelDto;
import com.baby.babycareproductsshop.review.model.ReviewInsDto;
import com.baby.babycareproductsshop.review.model.ReviewSelDto;
import com.baby.babycareproductsshop.review.model.ReviewSelVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@Tag(name = "리뷰 API", description = "리뷰 관련 파트")
public class ReviewController {

    private final ReviewService service;

    @PostMapping("/{iproduct}")
    @Operation(summary = "리뷰 작성", description = "리뷰 작성 절차")
    public ResVo insReview(@PathVariable int iproduct,
                           @RequestPart(required = false) List<MultipartFile> reviewPics,
                           @RequestPart @Valid ReviewInsDto dto) {
        if (reviewPics != null && reviewPics.size() >= 6) {
            throw new RestApiException(AuthErrorCode.NOT_ALLOWED_PICS_SIZE);
        }
        dto.setIproduct(iproduct);
        dto.setPics(reviewPics);
        return service.insReview(dto);

    }

    @GetMapping
    @Operation(summary = "리뷰 목록", description = "리뷰 전체 리스트")
    public List<ReviewSelVo> getReview(ReviewSelDto dto) {
        try {
            if (Utils.isNotNull(dto)) {
                List<ReviewSelVo> list = service.getReview(dto);
                return list;
            } else {
                throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @DeleteMapping
    @Operation(summary = "리뷰 삭제", description = "리뷰 삭제 절차")
    public ResVo delReview(ReviewDelDto dto) {
        try {
            if (Utils.isNotNull(dto)) {
                return service.delReview(dto);
            } else {
                throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
}
