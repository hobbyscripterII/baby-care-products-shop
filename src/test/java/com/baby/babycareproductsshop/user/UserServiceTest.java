package com.baby.babycareproductsshop.user;


import com.baby.babycareproductsshop.common.AppPropertiesTest;
import com.baby.babycareproductsshop.common.MyCookieUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.ProductWishListMapper;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import com.baby.babycareproductsshop.security.JwtTokenProvider;
import com.baby.babycareproductsshop.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(SpringExtension.class)
@Import(UserService.class)
class UserServiceTest {
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private ProductWishListMapper wishListMapper;
    @MockBean
    private UserAddressMapper addressMapper;
    @MockBean
    private UserChildMapper childMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtTokenProvider jwtTokenProvider;
    @MockBean
    private AppPropertiesTest appPropertiesTest;
    @MockBean
    private MyCookieUtils myCookieUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Autowired
    private UserService service;


    @Test
    @DisplayName("post sign up test")
    void postSignUp() {
        when(passwordEncoder.encode(any())).thenReturn("password");
        when(userMapper.insUser(any())).thenReturn(3);
        when(childMapper.insUserChildren(any())).thenReturn(2);
        when(addressMapper.insUserAddress(any())).thenReturn(1);

        UserSignUpDto dto = new UserSignUpDto();
        List<UserChildDto> children = new ArrayList<>();
        dto.setChildren(children);
        children.add(UserChildDto.builder()
                .gender("W")
                .ichildAge(1)
                .build());

        ResVo resVo = service.postSignUp(dto);

        verify(passwordEncoder).encode(any());
        verify(userMapper).insUser(any());
        verify(childMapper).insUserChildren(any());
        verify(addressMapper).insUserAddress(any());

    }

    @Test
    @DisplayName("get clause test")
    void getClause() {
        List<UserClauseVo> result = new ArrayList<>();
        when(userMapper.selClause()).thenReturn(result);
        service.getClause();
        verify(userMapper).selClause();
    }

    @Test
    @DisplayName("post check uid test")
    void postCheckUid() {
        when(userMapper.selSignInInfoByUid(any())).thenReturn(null);
        UserCheckUidDto dto = new UserCheckUidDto();
        dto.setUid("dksl1234");
        service.postCheckUid(dto);
        verify(userMapper).selSignInInfoByUid(any());
    }

    @Test
    @DisplayName("post sign in test")
    void postSignIn() { //작성 미완

    }


    @Test
    void getMyInfo() {
        when(authenticationFacade.getLoginUserPk()).thenReturn(1);
        UserSelMyInfoVo vo = new UserSelMyInfoVo();
        vo.setNm("한별");
        when(userMapper.selMyInfo(1)).thenReturn(vo);
        when(wishListMapper.selWishList(1)).thenReturn(new ArrayList<>());

        UserSelMyInfoVo result = service.getMyInfo();

        assertEquals("한별", result.getNm());
    }

    @Test
    void postCheckUpw() {
    }

    @Test
    void putUserInfo() {
    }

    @Test
    void signout() {
    }

    @Test
    void unregister() {
    }

    @Test
    void postUserAddress() {
    }

    @Test
    void getUserAddress() {
    }

    @Test
    void putUserAddress() {
    }

    @Test
    void delUserAddress() {
    }
}