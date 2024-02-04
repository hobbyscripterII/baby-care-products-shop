package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //---검색기능

    List<ProductSearchVo> search(ProductSearchDto dto);

    //-----메인화면
    List<ProductMainSelVo> maimSelVo();
    List<ProductMainSelVo> selProductMainByAge(ProductMainSelDto dto); // 로그인시
    List<ProductMainSelVo> SelPopProduct (); // 인기
    List<ProductMainSelVo> SelNewProduct (); // 신상품


    // 유저자녀나이
    Integer userChildAge(int iuser);

    //상품 사진넣기
    List<String> selProductPics (int iproduct);
    //-- 상품조회페이지
    List<ProductListSelVo> getProductByAgeRange(ProductListDto dto);



    //----장바구니
    List<ProductBasketSelVo> selProductBasket (ProductBasketSelDto dto);
    int delBasket(ProductBasketDelDto dto); // 여러개 삭제

    int insBasket (ProductBasketInsDto dto); // 넣고
    Integer selProductCntBasket (ProductBasketInsDto dto); //갯수
    int uptBasketProductCnt(ProductBasketInsDto dto); // 수정

    //-----상품정보 , 리뷰갯수 별점평균
    ProductSelVo selProductInformation(int iproduct, int iuser);
    ProductAverageSelVo selProductAverage(int iproduct);


    //----------------찜하기 기능
    int insertLikeProduct (ProductLikeDto dto);
    int deleteLikeProduct (ProductLikeDto dto);

    //---------------

    int insProduct(ProductInsDto dto);
    int insProductPics(ProductInsDto dto);
    int updProductRepPic(ProductInsDto dto);
    int updProductDetails(ProductInsDto dto);
}
