package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.CharEncodingConfig;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.security.JwtAuthenticationFilter;
import com.baby.babycareproductsshop.security.SecurityConfiguration;
import com.baby.babycareproductsshop.user.model.UserInsAddressDto;
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
    void getUserAddress() {
    }

    @Test
    void putUserAddress() {
    }

    @Test
    void delUserAddress() {
    }
}