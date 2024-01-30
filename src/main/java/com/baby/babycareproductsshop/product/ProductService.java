package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.*;
import com.baby.babycareproductsshop.review.model.ReviewPicsVo;
import com.baby.babycareproductsshop.review.model.ReviewSelVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;
    private final AuthenticationFacade facade;
    //---------- 검색기능
    public List<ProductSearchVo> searchProductSelVo(ProductSearchDto dto) {
        List<ProductSearchVo> searchVoList = productMapper.search(dto);
        return searchVoList;
    }
    //---------- 비로그인메인화면
    public List<ProductMainSelVo> productMainSelVo( ) {
       List<ProductMainSelVo> list = productMapper.maimSelVo();
       return list;
    }
    //-- 로그인
    public List<ProductMainSelVo> productMainLoginSelVo () { // 로그인
        ProductMainSelDto dto = new ProductMainSelDto();
        dto.setIuser(facade.getLoginUserPk());
        Integer userChildAge = productMapper.userChildAge(dto.getIuser());
        dto.setRecommandAge(userChildAge);
        List<ProductMainSelVo> list = productMapper.selProductMainByAge(dto);
        return list;
    }
    // 인기 뉴 상품
    public List<ProductMainSelVo> productPopNewSelVo() { // 인기 뉴상품
        List<ProductMainSelVo> popList = productMapper.SelPopProduct();
        List<ProductMainSelVo> newList = productMapper.SelNewProduct();

        List<ProductMainSelVo> list = Stream.concat(popList.stream()
                ,newList.stream())
                .distinct()
                .limit(16)
                .collect(Collectors.toList());
        return list;
    }

    //------ 월령별 화면? 카테고리 느낌인거같은데

    public List<ProductListSelVo> getProductByAgeRange(ProductListDto dto) {
        List<ProductListSelVo> list = productMapper.getProductByAgeRange(dto);
        return list;
    }

    //---- 상품 상세 정보
    public List<ProductSelVo> selProduct(ProductSelDto dto) {
        ProductAverageSelVo productProductAverageSelVo = productMapper.selProductAverage(dto.getIproduct());

        List<Integer> productReview = new ArrayList<>();
        List<Integer> iProductList = new ArrayList<>();
        Map<Integer, ReviewSelVo> reviewMap = new HashMap<>();
        Map<Integer, ProductSelVo> ProductSelVoMap = new HashMap<>();
        List<ReviewSelVo> reviewSelVoList = productReviewMapper.getProductReview(dto);
        List<ProductSelVo> Product = productMapper.selProductInformation(dto.getIproduct());

        for(ReviewSelVo vo : reviewSelVoList){
            productReview.add(vo.getIreview());
            reviewMap.put(vo.getIreview(), vo);
        }

        if(!productReview.isEmpty()){
            List<ReviewPicsVo> reviewPicsVoList = productReviewMapper.getProductReviewPics(productReview);

            for(ReviewPicsVo vo : reviewPicsVoList){
                ReviewSelVo reviewSelVo = reviewMap.get(vo.getIreview());
                List<String> pics = reviewSelVo.getPics();
                pics.add(vo.getReviewPic());
            }
        }

        if (!reviewSelVoList.isEmpty()) {
            for (ProductSelVo vo : Product) {
                vo.setScoreAvg(productProductAverageSelVo.getAvgProductScore());
                vo.setReviewCnt(productProductAverageSelVo.getReviewCnt());
                iProductList.add(vo.getIproduct());
                vo.setReviewSelVo(reviewSelVoList);

                ProductSelVoMap.put(vo.getIproduct(), vo);
            }
            if (!iProductList.isEmpty()) {
                List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iProductList);
                for (ProductPicsVo vo : productPicsVoList) {
                    ProductSelVo productSelVo = ProductSelVoMap.get(vo.getIproduct());
                    List<String> pics = productSelVo.getProductPics();
                    pics.add(vo.getProductPic());
                }
            }
        }

        return Product;
    }


    //---------- 장바구니
    public List<ProductBasketSelVo> productBasketSelVo(ProductBasketSelDto dto) {
        dto.setIuser(facade.getLoginUserPk());
        return productMapper.selProductBasket(dto);
    }

    public ResVo delBasket(List<Integer> iproducts) { // 장바구니 삭제
        int delBasket = productMapper.delBasket(iproducts);
        return new ResVo(delBasket);
    }

    public ResVo insBasket(ProductBasketInsDto dto) { // 장바구니 넣기
        dto.setIuser(facade.getLoginUserPk());
        Integer productCnt = productMapper.selProductCntBasket(dto);
        if (productCnt == null) { //장바구니가 없다
            int result = productMapper.insBasket(dto);
            return new ResVo(dto.getProductCnt());
        }

        dto.setProductCnt(dto.getProductCnt() + productCnt); // 기존에 담겨있는개수 + 내가 담아줌.
        int upt = productMapper.uptBasketProductCnt(dto); //보내주고
        return new ResVo(dto.getProductCnt()); // 리턴값으로
    }

    public ResVo uptBasket(ProductBasketInsDto dto) { //장바구니 안에서 값 수정
        dto.setIuser(facade.getLoginUserPk());
        int upt = productMapper.uptBasketProductCnt(dto);
        return new ResVo(dto.getProductCnt());

    }


    //---------- 찜하기

    public ResVo wishProduct(ProductLikeDto dto) {
        dto.setIuser(facade.getLoginUserPk());

        int result = productMapper.deleteLikeProduct(dto);
        if (result == 1) {
            return new ResVo(Const.FAIL);
        }
        int result2 = productMapper.insertLikeProduct(dto);
        return new ResVo(Const.SUCCESS);
    }


    //----------장바구니 값 수정
    public ResVo uptBasketProductCnt (ProductBasketInsDto dto) {
        int result = productMapper.uptBasketProductCnt(dto);
        return new ResVo(result);
    }


}
