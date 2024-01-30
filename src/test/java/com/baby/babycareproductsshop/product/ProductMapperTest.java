package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.product.model.ProductMainSelDto;
import com.baby.babycareproductsshop.product.model.ProductMainSelVo;
import com.baby.babycareproductsshop.review.ReviewMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

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
    void delBasket() {
    }

    @Test
    void insBasket() {
    }

    @Test
    void selProductCntBasket() {
    }

    @Test
    void uptBasketProductCnt() {
    }

    @Test
    void selProductInformation() {
    }

    @Test
    void selProductAverage() {
    }

    @Test
    void insertLikeProduct() {
    }

    @Test
    void deleteLikeProduct() {
    }
}