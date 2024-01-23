package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.CharEncodingConfig;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.security.JwtAuthenticationFilter;
import com.baby.babycareproductsshop.security.SecurityConfiguration;
import com.baby.babycareproductsshop.user.model.UserInsAddressDto;
import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import com.baby.babycareproductsshop.user.model.UserUpdAddressDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CharEncodingConfig.class)
@WebMvcTest(controllers = UserAddressController.class,
//        excludeAutoConfiguration = SecurityConfiguration.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
class UserAddressControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService service;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser
    void postUserAddress() throws Exception {
        ResVo result = new ResVo(1);
        given(service.postUserAddress(any())).willReturn(result);
        UserInsAddressDto dto = new UserInsAddressDto();
        dto.setAddress("주소");
        dto.setIuser(1);
        dto.setAddressDetail("상세 주소");
        dto.setZipCode("1234");
        String json = mapper.writeValueAsString(dto);
        mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/address")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                                .with(csrf())
                )
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).postUserAddress(any());
    }



    @Test
    @WithMockUser
    void getUserAddress() throws Exception {
        UserSelAddressVo vo1 = new UserSelAddressVo();
        vo1.setAddress("주소");
        vo1.setIaddress(1);
        vo1.setAddressDetail("상세 주소");
        vo1.setZipCode("1234");

        UserSelAddressVo vo2 = new UserSelAddressVo();
        vo2.setAddress("주소22");
        vo2.setIaddress(2);
        vo2.setAddressDetail("상세 주소22");
        vo2.setZipCode("2222");

        List<UserSelAddressVo> result = new ArrayList<>();
        result.add(vo1);
        result.add(vo2);

        given(service.getUserAddress()).willReturn(result);

        mvc.perform(
               MockMvcRequestBuilders.get("/api/user/address")
        ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).getUserAddress();
    }

    @Test
    @WithMockUser
    void putUserAddress() throws Exception {
        ResVo result = new ResVo(1);
        UserUpdAddressDto dto = new UserUpdAddressDto();
        dto.setIuser(1);
        dto.setAddress("주소");
        dto.setIaddress(1);
        dto.setAddressDetail("상세 주소");
        dto.setZipCode("1234");

        given(service.putUserAddress(dto)).willReturn(result);

        mvc.perform(
                MockMvcRequestBuilders.put("/api/user/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                        .with(csrf())
        ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(result)))
                .andDo(print());

        verify(service).putUserAddress(dto);
    }

    @Test
    void delUserAddress() {
    }
}