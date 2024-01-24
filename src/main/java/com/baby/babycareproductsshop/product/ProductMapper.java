package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    //---검색기능

    List<ProductSearchVo> keyword(ProductSearchPriceDto dto);

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
    int selPaymentAmount();
    int delBasket(List<Integer> iproducts);

    int insBasket (ProductBasketInsDto dto);
    Integer selProductCntBasket (ProductBasketInsDto dto);
    int uptBasketProductCnt(ProductBasketInsDto dto);

    //-----상품정보 , 리뷰갯수 별점평균
    List <ProductSelVo>selProductInformation(int iproduct);
    ProductProductAverageSelVo selProductAverage(int iproduct);


    //----------------찜하기 기능
    int insertLikeProduct (ProductLikeDto dto);
    int deleteLikeProduct (ProductLikeDto dto);

    //---------------
    int insOrder(wwww wwww);
    /*
    int insOrderDetails(ddd);

     */
    int delBasketAll (int iuser);



}
