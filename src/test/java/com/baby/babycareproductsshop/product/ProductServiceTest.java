package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.ProductBasketDelDto;
import com.baby.babycareproductsshop.product.model.ProductBasketInsDto;
import com.baby.babycareproductsshop.product.model.ProductBasketSelVo;
import com.baby.babycareproductsshop.review.ReviewMapper;
import com.baby.babycareproductsshop.review.ReviewService;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @MockBean ProductMapper mapper;
    @MockBean MyFileUtils myFileUtils;
    @MockBean AuthenticationFacade authenticationFacade;
    @Autowired ProductService service;


    @Test
    void searchProductSelVo() {
    }

    @Test
    void productMainSelVo() {
    }

    @Test
    void productMainLoginSelVo() {
    }

    @Test
    void productPopNewSelVo() {
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
    @DisplayName("장바구니 물품 삭제")
    void delBasket() {
        when(mapper.delBasket(any())).thenReturn(1);

        List<Integer> iproduct = new ArrayList<>();
        iproduct.add(5);
        iproduct.add(6);

        ProductBasketDelDto dto = new ProductBasketDelDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());
        dto.setIproduct(iproduct);

        int delBasket = mapper.delBasket(dto);
        assertEquals(2,delBasket);









    }

    @Test
    @DisplayName("장바구니 물품 등록")
    void insBasket() {
    }

    @Test
    @DisplayName("장바구니 물품 수량 수정")
    void uptBasket() {
    }

    @Test
    @DisplayName("찜하기")
    void wishProduct() {
    }
}