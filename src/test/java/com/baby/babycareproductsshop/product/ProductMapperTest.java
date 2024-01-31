package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.*;
import com.baby.babycareproductsshop.review.ReviewMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductMapperTest {
    @Autowired
    private ProductMapper mapper;

    @DisplayName("리뷰 작성 테스트")


    @Test
    void keyword() {
    }

    @DisplayName("메인 화면 테스트")
    @Test
    void maimSelVo() {
        ProductMainSelVo vo = new ProductMainSelVo();
        ProductMainSelVo vo2 = new ProductMainSelVo();

        ProductMainSelDto dto = new ProductMainSelDto();
        dto.setIuser(21);

        List<ProductMainSelVo> selVos = mapper.selProductMainByAge(dto);
        selVos.add(vo);




    }

    @Test
    void selProductMainByAge() {
    }

    @Test
    void userChildAge() {
    }

    @Test
    void selProductPics() {
    }

    @Test
    void getProductByAgeRange() {
    }

    @Test
    void selProductBasket() {
    }

    @Test
    @DisplayName("장바구니 물품 삭제")
    void delBasket() {
       List<Integer> iproducts = new ArrayList<>();
       iproducts.add(1);
        ProductBasketDelDto dto = new ProductBasketDelDto();
        dto.setIuser(21);
        dto.setIproduct(iproducts);
       int delProductBasket = mapper.delBasket(dto);
        assertEquals(iproducts.size(),delProductBasket);
    }

    @Test
    @DisplayName("물품 장바구니 등록")
    void insBasket() {
        ProductBasketInsDto dto = new ProductBasketInsDto();
        dto.setIuser(20);
        dto.setIproduct(1);
        dto.setProductCnt(4);

        int insProductBasket = mapper.insBasket(dto);
        assertEquals(1,insProductBasket);



    }

    @Test
    void selProductCntBasket() {
    }

    @Test
    @DisplayName("상품물품수량")
    void uptBasketProductCnt() {
        ProductBasketInsDto dto = new ProductBasketInsDto();
        dto.setIuser(21);
        dto.setIproduct(1);
        dto.setProductCnt(2);

       int uptBasketProductCnt = mapper.uptBasketProductCnt(dto);
        assertEquals(1,uptBasketProductCnt);

    }

    @Test
    void selProductInformation() {
    }

    @Test
    void selProductAverage() {
    }

    @Test
    void insertLikeProduct() {
        ProductLikeDto dto = new ProductLikeDto();
        dto.setIuser(21);
        dto.setIproduct(16);

        int productLike = mapper.insertLikeProduct(dto);
        assertEquals(1,productLike);

    }

    @Test
    void deleteLikeProduct() {
        ProductLikeDto dto = new ProductLikeDto();
        dto.setIuser(21);
        dto.setIproduct(15);

        int productLikeDel = mapper.deleteLikeProduct(dto);
        assertEquals(1,productLikeDel);
    }
}