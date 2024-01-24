package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //---검색기능

    List<ProductSearchVo> keyword(ProductSearchDto dto);

    //-----메인화면
    List<ProductMainSelVo> maimSelVo();
    List<ProductMainSelVo> selProductMainByAge(ProductMainSelDto dto); // 로그인시

    // 유저자녀나이
    Integer userChildAge(int iuser);

    //상품 사진넣기
    List<ProductPicsVo> selProductPics (List<Integer> iproduct);

    // 리뷰사진넣기
    List<ProductPicsVo> selReviewPicsAll(List<Integer> ireview );

    //-- 상품조회페이지
    List<ProductMainSelVo> getProductByAgeRange(productByAgeRangeDto dto);



    //----장바구니
    List<ProductBasketSelVo> selProductBasket (ProductBasketSelDto dto);
    int delBasket(List<Integer> iproducts); // 여러개 삭제

    int insBasket (ProductBasketInsDto dto); // 넣고
    Integer selProductCntBasket (ProductBasketInsDto dto); //갯수
    int uptBasketProductCnt(ProductBasketInsDto dto); // 수정

    //-----상품정보 , 리뷰갯수 별점평균
    List <ProductSelVo>selProductInformation(int iproduct);
    ProductAverageSelVo selProductAverage(int iproduct);


    //----------------찜하기 기능
    int insertLikeProduct (ProductLikeDto dto);
    int deleteLikeProduct (ProductLikeDto dto);

    //---------------




}
