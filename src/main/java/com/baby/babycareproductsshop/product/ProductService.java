package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.product.model.*;
import com.baby.babycareproductsshop.review.model.ReviewPicsVo;
import com.baby.babycareproductsshop.review.model.ReviewSelVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade facade;

    //---------- 검색기능
    public List<ProductSearchVo> searchProductSelVo(ProductSearchDto dto) {
        try {
            List<ProductSearchVo> searchVoList = productMapper.search(dto);
            return searchVoList;
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    //---------- 비로그인메인화면
    public List<ProductMainSelVo> productMainSelVo() {
        try {
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
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    //-- 로그인
    public List<ProductMainSelVo> productMainLoginSelVo() {
        try {
            ProductMainSelDto dto = new ProductMainSelDto();
            dto.setIuser(facade.getLoginUserPk());

            List<Integer> userChildAge = productMapper.userChildAge(dto.getIuser())
                    .stream().collect(Collectors.toList());

//            List<Integer> userChildAgelist = new ArrayList<>();
//            for (int i = 0; i <userChildAge.size(); i++) {
//                userChildAgelist.add(userChildAge.get(i));
//            }

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
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }

    }

    // 인기 신상품
    public List<ProductMainSelVo> productPopNewSelVo() {
        try {
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
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }

    }

    //------ 상품조회페이지

    public List<ProductListSelVo> getProductList(ProductListDto dto) {
        try {
            List<ProductListSelVo> list = productMapper.getProductList(dto);
            return list;
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    //---- 상품 상세 정보
    public ProductSelVo selProduct(ProductSelDto dto) {
        try {
            int iuser = facade.getLoginUserPk();
            ProductAverageSelVo productProductAverageSelVo = productMapper.selProductAverage(dto.getIproduct());
            List<Integer> productReview = new ArrayList<>();
            List<Integer> iProductList = new ArrayList<>();
            Map<Integer, ReviewSelVo> reviewMap = new HashMap<>();
            Map<Integer, ProductSelVo> ProductSelVoMap = new HashMap<>();
            List<ReviewSelVo> reviewSelVoList = productReviewMapper.getProductReview(dto);
            ProductSelVo result = productMapper.selProductInformation(dto.getIproduct(), iuser);
            for (ReviewSelVo vo : reviewSelVoList) {
                productReview.add(vo.getIreview());
                reviewMap.put(vo.getIreview(), vo);
            }
            if (!productReview.isEmpty()) {
                List<ReviewPicsVo> reviewPicsVoList = productReviewMapper.getProductReviewPics(productReview);
                for (ReviewPicsVo vo : reviewPicsVoList) {
                    ReviewSelVo reviewSelVo = reviewMap.get(vo.getIreview());
                    List<String> pics = reviewSelVo.getPics();
                    pics.add(vo.getReviewPic());
                }
            }
            if (!reviewSelVoList.isEmpty()) {
                result.setScoreAvg(productProductAverageSelVo.getAvgProductScore());
                result.setReviewCnt(productProductAverageSelVo.getReviewCnt());
                iProductList.add(result.getIproduct());
                result.setReviewSelVo(reviewSelVoList);
            }
            ProductSelVoMap.put(result.getIproduct(), result);
            List<String> productPicsVoList = productMapper.selProductPics(dto.getIproduct());
            result.setProductPics(productPicsVoList);
            return result;
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }


//        int iuser = facade.getLoginUserPk();
//        ProductAverageSelVo productProductAverageSelVo = productMapper.selProductAverage(dto.getIproduct());
//        List<Integer> productReview = new ArrayList<>();
//        List<Integer> iProductList = new ArrayList<>();
//        Map<Integer, ReviewSelVo> reviewMap = new HashMap<>();
//        Map<Integer, ProductSelVo> ProductSelVoMap = new HashMap<>();
//        List<ReviewSelVo> reviewSelVoList = productReviewMapper.getProductReview(dto);
//        ProductSelVo result = productMapper.selProductInformation(dto.getIproduct(), iuser);
//        for (ReviewSelVo vo : reviewSelVoList) {
//            productReview.add(vo.getIreview());
//            reviewMap.put(vo.getIreview(), vo);
//        }
//        if (!productReview.isEmpty()) {
//            List<ReviewPicsVo> reviewPicsVoList = productReviewMapper.getProductReviewPics(productReview);
//            for (ReviewPicsVo vo : reviewPicsVoList) {
//                ReviewSelVo reviewSelVo = reviewMap.get(vo.getIreview());
//                List<String> pics = reviewSelVo.getPics();
//                pics.add(vo.getReviewPic());
//            }
//        }
//        if (!reviewSelVoList.isEmpty()) {
//            result.setScoreAvg(productProductAverageSelVo.getAvgProductScore());
//            result.setReviewCnt(productProductAverageSelVo.getReviewCnt());
//            iProductList.add(result.getIproduct());
//            result.setReviewSelVo(reviewSelVoList);
//        }
//        ProductSelVoMap.put(result.getIproduct(), result);
//        List<String> productPicsVoList = productMapper.selProductPics(dto.getIproduct());
//        result.setProductPics(productPicsVoList);
//        return result;
    }


    // 장바구니
    public List<ProductBasketSelVo> productBasketSelVo() {
        try {
            ProductBasketSelDto dto = new ProductBasketSelDto();
            dto.setIuser(facade.getLoginUserPk());
            return productMapper.selProductBasket(dto);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    // 장바구니 삭제
    public ResVo delBasket(List<Integer> iproduct) {
        try {
            ProductBasketDelDto dto = new ProductBasketDelDto();
            dto.setIuser(facade.getLoginUserPk());
            dto.setIproduct(iproduct);
            int delBasket = productMapper.delBasket(dto);
            return new ResVo(delBasket);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }

    }

    // 장바구니 넣기
    public ResVo insBasket(ProductBasketInsDto dto) {
        try {
            dto.setIuser(facade.getLoginUserPk());
            Integer productCnt = productMapper.selProductCntBasket(dto);
            if (productCnt == null) {
                int result = productMapper.insBasket(dto);
                return new ResVo(dto.getProductCnt());
            }
            dto.setProductCnt(dto.getProductCnt() + productCnt);
            int upt = productMapper.uptBasketProductCnt(dto);
            return new ResVo(dto.getProductCnt());
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }


    }

    //장바구니안에서 상품 수량 수정
    public ResVo uptBasket(ProductBasketInsDto dto) {
        try {
            dto.setIuser(facade.getLoginUserPk());
            Integer productCnt = productMapper.selProductCntBasket(dto);
            if (productCnt == null) {
                throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
            }
            int upt = productMapper.uptBasketProductCnt(dto);
            return new ResVo(dto.getProductCnt());
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }

    }


    //---------- 찜하기
    public ResVo wishProduct(ProductLikeDto dto) {
        try {
            dto.setIuser(facade.getLoginUserPk());
            int result = productMapper.deleteLikeProduct(dto);
            if (result == 1) {
                return new ResVo(Const.FAIL);
            }
            int result2 = productMapper.insertLikeProduct(dto);
            return new ResVo(Const.SUCCESS);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @Transactional
    public ResVo postProduct(List<MultipartFile> pics, MultipartFile productDetails, ProductInsDto dto) {
        int insResult = productMapper.insProduct(dto);
        String target = "/product/" + dto.getIproduct();
        List<String> savedPics = new ArrayList<>();
        for (MultipartFile file : pics) {
            String fileNm = myFileUtils.transferTo(file, target);
            savedPics.add(fileNm);
        }
        String detailsFileNm = myFileUtils.transferTo(productDetails, target);
        dto.setProductDetails(detailsFileNm);
        dto.setPics(savedPics);
        int insPicsResult = productMapper.insProductPics(dto);
        int updRepPicResult = productMapper.updProductRepPic(dto);
        return new ResVo(Const.SUCCESS);
    }

    @Transactional
    public ResVo patchProductPics(List<MultipartFile> pics, MultipartFile productDetail, int iproduct) {
        String target = "/product/" + iproduct;
        myFileUtils.delDirTrigger(target);
        String saveFileNm = myFileUtils.transferTo(productDetail, target);
        List<String> savePics = new ArrayList<>();
        for (MultipartFile pic : pics) {
            String fileNm = myFileUtils.transferTo(pic, target);
            savePics.add(fileNm);
        }
        ProductInsDto dto = new ProductInsDto();
        dto.setRepPic(savePics.get(0));
        dto.setIproduct(iproduct);
        dto.setProductDetails(saveFileNm);
        dto.setPics(savePics);
        int updRepPicResult = productMapper.updProductRepPic(dto);
        int insPicsResult = productMapper.insProductPics(dto);
        int updDetailsResult = productMapper.updProductDetails(dto);
        return new ResVo(1);
    }
}
