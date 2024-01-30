package com.baby.babycareproductsshop.review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Data
@Schema(title = "리뷰 작성 시 필요한 데이터")
public class ReviewInsDto {
    @Schema(title = "주문상세 KEY")
    @Positive
    private int idetails;
    @Schema(title = "주문 PK")
    @Positive
    private int iorder;
    @JsonIgnore
    private int iproduct;
    @JsonIgnore
    private int iuser;
    @JsonIgnore
    private String repReviewPic;
    @Schema(title = "리뷰 내용")
    @NotNull
    private String contents;
    @Schema(title = "리뷰 별점" ,defaultValue = "5")
    @Min(1) @Max(5)
    private int productScore;
    @JsonIgnore
    private List<MultipartFile> pics;
}
