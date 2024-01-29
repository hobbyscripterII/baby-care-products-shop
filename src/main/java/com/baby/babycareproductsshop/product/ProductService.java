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

    //---------- 로그인메인화면
    public List<ProductMainSelVo> productMainSelVo(ProductMainSelDto dto) {
        dto.setIuser(facade.getLoginUserPk());


        List<ProductMainSelVo> mainlist;
        if (dto.getIuser() > 0) {
            Integer userChildAge = productMapper.userChildAge(dto.getIuser());
            dto.setRecommandAge(userChildAge);
            mainlist = productMapper.selProductMainByAge(dto);
        } else {
            mainlist = productMapper.maimSelVo();
        }

        // 인기
        List<ProductMainSelVo> selPopProduct = productMapper.SelPopProduct();
        // 최신
        List<ProductMainSelVo> selNewProduct = productMapper.SelNewProduct();

        List<ProductMainSelVo> stream3 = Stream.concat(mainlist.stream(),selNewProduct.stream())
                .distinct().limit(16)
                .collect(Collectors.toList());

        List<ProductMainSelVo> stream = Stream.concat(stream3.stream(),selPopProduct.stream())
                .distinct().limit(24)
                .collect(Collectors.toList());


        return stream;

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
        if (productCnt == null) {
            int result = productMapper.insBasket(dto);
            return new ResVo(dto.getProductCnt());
        }
        dto.setProductCnt(dto.getProductCnt() + productCnt);
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


    //----------장바구니에서 주문으로 넘겨줄 데이터


}
