package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final AuthenticationFacade facade;
    //---------- 검색기능
    public List<ProductSearchVo> searchProductSelVo(ProductSearchPriceDto dto) {

        List<ProductSearchVo> searchVoList = productMapper.keyword(dto);
        List<Integer> iproductList = new ArrayList<>();
        Map<Integer, ProductSearchVo> keywordMap = new HashMap<>();
        for (ProductSearchVo vo : searchVoList) {
            iproductList.add(vo.getIproduct());
            keywordMap.put(vo.getIproduct(), vo);
        }
        if (iproductList.size() > 0) {
            List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
            for (ProductPicsVo vo : productPicsVoList) {
                ProductSearchVo searchVo = keywordMap.get(vo.getIproduct());
                List<String> pics = searchVo.getPics();
                pics.add(vo.getProductPic());
            }
        }
        return searchVoList;
    }


    //---------- 로그인메인화면
    public List<ProductMainSelVo> productMainSelVo2(ProductMainSelDto dto) {
        dto.setIuser(facade.getLoginUserPk());
        Integer userChildAge  = productMapper.userChildAge(dto.getIuser());
        if (userChildAge  == null) {
            List<ProductMainSelVo> mainlist = productMapper.maimSelVo();
            List<Integer> iproductList = new ArrayList<>();
            Map<Integer, ProductMainSelVo> mainSelVoMap = new HashMap<>();
            for (ProductMainSelVo vo : mainlist) {
                iproductList.add(vo.getIproduct());
                mainSelVoMap.put(vo.getIproduct(), vo);
            }
            if (iproductList.size() > 0) {
                List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
                for (ProductPicsVo vo : productPicsVoList) {
                    ProductMainSelVo mainSelVo = mainSelVoMap.get(vo.getIproduct());
                    List<String> pics = mainSelVo.getProductPic();
                    pics.add(vo.getProductPic());
                }
            }
            return mainlist;
        }
        dto.setRecommandAge(userChildAge );
        List<ProductMainSelVo> mainlist = productMapper.selProductMainByAge(dto);
        List<Integer> iproductList = new ArrayList<>();
        Map<Integer, ProductMainSelVo> mainSelVoMap = new HashMap<>();
        for (ProductMainSelVo vo : mainlist) {
            iproductList.add(vo.getIproduct());
            mainSelVoMap.put(vo.getIproduct(), vo);
        }
        if (iproductList.size() > 0) {
            List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
            for (ProductPicsVo vo : productPicsVoList) {
                ProductMainSelVo mainSelVo = mainSelVoMap.get(vo.getIproduct());
                List<String> pics = mainSelVo.getProductPic();
                pics.add(vo.getProductPic());
            }
        }
        return mainlist;
    }

    //------ 월령별 화면? 카테고리 느낌인거같은데

    public List<ProductMainSelVo> getProductByAgeRange(productByAgeRangeDto dto) {
        List<ProductMainSelVo> list = productMapper.getProductByAgeRange(dto);
        List<Integer> iproductList = new ArrayList<>();
        Map<Integer, ProductMainSelVo> mainSelVoMap = new HashMap<>();
        for (ProductMainSelVo vo : list) {
            iproductList.add(vo.getIproduct());
            mainSelVoMap.put(vo.getIproduct(), vo);
        }
        if (iproductList.size() > 0) {
            List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
            for (ProductPicsVo vo : productPicsVoList) {
                ProductMainSelVo mainSelVo = mainSelVoMap.get(vo.getIproduct());
                List<String> pics = mainSelVo.getProductPic();
                pics.add(vo.getProductPic());
            }
        }
        return list;
    }

    //---- 상품 상세 정보
    public List<ProductSelVo> selProduct(ProductSelDto dto) {
        ProductProductAverageSelVo productProductAverageSelVo = productMapper.selProductAverage(dto.getIproduct());
        List<Integer> iproductList = new ArrayList<>();
        List<Integer> ireviewList = new ArrayList<>();
        Map<Integer, ProductSelVo> ProductSelVoMap = new HashMap<>();
        Map<Integer, ProductSelVo> reviewSelVoMap = new HashMap<>();

        List<ProductSelVo> list = productMapper.selProductInformation(dto.getIproduct());
        for (ProductSelVo vo : list) {
            vo.setAvgProductScore(productProductAverageSelVo.getAvgProductScore());
            vo.setCountIreview(productProductAverageSelVo.getCountIreview());
            iproductList.add(vo.getIproduct());
            ireviewList.add(vo.getIreview());
            ProductSelVoMap.put(vo.getIproduct(), vo);
            reviewSelVoMap.put(vo.getIreview(),vo);

        }

        if (iproductList.size() > 0) {

            List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
            List<ProductPicsVo> reviewPicsVoList = productMapper.selReviewPicsAll(ireviewList);

            for (ProductPicsVo vo : productPicsVoList) {
                ProductSelVo productSelVo = ProductSelVoMap.get(vo.getIproduct());
                List<String> pics = productSelVo.getProductPics();
                pics.add(vo.getProductPic());
            }
            for (ProductPicsVo vo : reviewPicsVoList) {
                ProductSelVo productSelVo = reviewSelVoMap.get(vo.getIreview());
                List<String> pics = productSelVo.getReviewPic();
                pics.add(vo.getReviewPic());
            }



        }
        return list;
    }


    //---------- 장바구니
    public List<ProductBasketSelVo> productBasketSelVo(ProductBasketSelDto dto) {
         dto.setIuser(facade.getLoginUserPk());
        int result = productMapper.selPaymentAmount();
        List<ProductBasketSelVo> list = productMapper.selProductBasket(dto);
        List<Integer> iproductList = new ArrayList<>();
        Map<Integer, ProductBasketSelVo> mainSelVoMap = new HashMap<>();
        for (ProductBasketSelVo vo : list) {
            vo.setPaymentAmount(result);
            iproductList.add(vo.getIproduct());
            mainSelVoMap.put(vo.getIproduct(), vo);
        }
        if (iproductList.size() > 0) {
            List<ProductPicsVo> productPicsVoList = productMapper.selProductPics(iproductList);
            for (ProductPicsVo vo : productPicsVoList) {
                ProductBasketSelVo basketSelVo = mainSelVoMap.get(vo.getIproduct());
                List<String> pics = basketSelVo.getProductPic();
                pics.add(vo.getProductPic());
            }
        }
        return list;
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
            return new ResVo(0);
        }
        int result2 = productMapper.insertLikeProduct(dto);
        return new ResVo(result2);

    }
}
