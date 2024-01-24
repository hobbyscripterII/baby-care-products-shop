package com.baby.babycareproductsshop.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(title = "검색 시 필요한 데이터")
public class ProductSearchDto {
    @Schema(title = "검색어")
    @JsonProperty(value = "검색어")
    private String keyword;

    @Schema(title = "최솟값")
    @JsonProperty(value = "최소 금액")
    private int minPrice;

    @Schema(title = "최댓값")
    @JsonProperty(value = "최대 금액")
    private int maxPrice;

    @Schema(title = "카테고리 PK")
    @JsonProperty(value = "카테고리")
    private List<Integer> category;

    @Schema(title = "정렬 값 (0 : 최신순, 1 :가격 높은순, 2 : 가격 낮은순")
    @JsonProperty(value = "정렬")
    private int sortBy ;

    @Schema(title = "대분류 PK")
    @JsonProperty(value = "대분류")
    private int imain;


    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount = 5;

    @Schema(title = "페이징처리")
    @JsonProperty(value = "페이지")
    public void setPage(int page) {
        this.startIdx = (page - 1) * rowCount;
    }



}
