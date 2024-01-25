package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.review.ReviewController;
import com.baby.babycareproductsshop.security.JwtAuthenticationFilter;
import com.baby.babycareproductsshop.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers = ReviewController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))

class ProductContollerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService service;
    @Autowired
    private ObjectMapper mapper;

    @DisplayName("GET / 상품 검색 API 테스트")
    @Test
    @WithMockUser
    void searchProduct() {

    }

    @DisplayName("GET / 메인화면 API 테스트")
    @Test
    @WithMockUser
    void getProduct() {
    }

    @DisplayName("GET / 상품 상세 조회 API 테스트")
    @Test
    @WithMockUser
    void getProductByAgeRange() {
    }

    @DisplayName("GET / 상품 상세 보기 API 테스트")
    @Test
    @WithMockUser
    void selProduct() {
    }

    @DisplayName("GET / 장바구니 목록 API 테스트")
    @Test
    @WithMockUser
    void selCartProduct() {
    }

    @DisplayName("DELETE / 장바구니 상품 삭제 API 테스트")
    @Test
    @WithMockUser
    void delCartProduct() {
    }

    @DisplayName("POST / 장바구니 상품 넣  API 테스트")
    @Test
    @WithMockUser
    void insCartProduct() {
    }

    @DisplayName("GET / 좋아요 API 테스트")
    @Test
    @WithMockUser
    void wishProduct() {
    }
}