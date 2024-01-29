package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.review.ReviewMapper;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @MockBean
    ProductMapper mapper;
    @MockBean
    MyFileUtils myFileUtils;
    @MockBean
    AuthenticationFacade authenticationFacade;

    @Test
    void searchProductSelVo() {
    }

    @Test
    void productMainSelVo2() {
    }

    @Test
    void getProductByAgeRange() {
    }

    @Test
    void selProduct() {
    }

    @Test
    void productBasketSelVo() {
    }

    @Test
    void delBasket() {
    }

    @Test
    void insBasket() {
    }

    @Test
    void wishProduct() {
    }
}