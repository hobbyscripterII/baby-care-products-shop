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

import java.util.*;
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
        List<ProductMainSelVo> popNewList = this.productPopNewSelVo();
        List<ProductMainSelVo> mainSelVo = new ArrayList<>();

        Set<Integer> popNewIds = new HashSet<>();
        for (ProductMainSelVo item : popNewList) {
            popNewIds.add(item.getIproduct());
        }

        int count = 0;
        while (mainSelVo.size() < 8 && count < list.size()) {
            ProductMainSelVo vo = list.get(count);
            if (!popNewIds.contains(vo.getIproduct())) {
                mainSelVo.add(vo);
            }
            count++;
        }
        return mainSelVo;
    }
    //-- 로그인
    public List<ProductMainSelVo> productMainLoginSelVo () {
        ProductMainSelDto dto = new ProductMainSelDto();
        dto.setIuser(facade.getLoginUserPk());
        Integer userChildAge = productMapper.userChildAge(dto.getIuser());
        dto.setRecommandAge(userChildAge);
        List<ProductMainSelVo> list = productMapper.selProductMainByAge(dto);

        List<ProductMainSelVo> popNewList = this.productPopNewSelVo();

        List<ProductMainSelVo> mainSelVo = new ArrayList<>();
        Set<Integer> popNewIds = new HashSet<>();

        for (ProductMainSelVo vo : popNewList) {
            popNewIds.add(vo.getIproduct());
        }

        int count = 0;

        while (mainSelVo.size() < 8 && count < list.size()) {
            ProductMainSelVo vo = list.get(count);
            if (!popNewIds.contains(vo.getIproduct())) {
                mainSelVo.add(vo);
            }
            count++;
        }

        return mainSelVo;
    }
    // 인기 신상품
    public List<ProductMainSelVo> productPopNewSelVo() {
        List<ProductMainSelVo> popList = productMapper.SelPopProduct();
        List<ProductMainSelVo> newList = productMapper.SelNewProduct();

        Set<Integer> popIds = new HashSet<>();
        List<ProductMainSelVo> list = new ArrayList<>();

        for (int i = 0; i < popList.size() && popIds.size() < 8; i++) {
            popIds.add(popList.get(i).getIproduct());
            list.add(popList.get(i));
        }

        int count = 0;
        while (list.size() < 16 && count < newList.size()) {
            ProductMainSelVo vo = newList.get(count);
            if (!popIds.contains(vo.getIproduct())) {
                list.add(vo);
            }
            count++;
        }
        return list;
    }

    //------ 상품조회페이지

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

    // 장바구니 삭제
    public ResVo delBasket(List<Integer> iproducts) {
        int delBasket = productMapper.delBasket(iproducts);
        return new ResVo(delBasket);
    }

    // 장바구니 넣기
    public ResVo insBasket(ProductBasketInsDto dto) {
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

    //장바구니안에서 상품 수량 수정
    public ResVo uptBasket(ProductBasketInsDto dto) {
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




}
