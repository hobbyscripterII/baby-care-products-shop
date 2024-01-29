package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.BaseIntegrationTest;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.user.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserIntegrationTest extends BaseIntegrationTest {

    @Test
    public void getClause() throws Exception {
        MvcResult mvcResult = mvc.perform(
                        MockMvcRequestBuilders.get("/api/user/sign-up")
                ).andExpect(status().isOk())
                .andReturn();

        String contents = mvcResult.getResponse().getContentAsString();
        List result = mapper.readValue(contents, List.class);

        assertEquals(2, result.size());
    }

    @Test
    public void postSignUpAndSignIn() throws Exception {
        UserCheckUidDto checkUidDto = new UserCheckUidDto();
        checkUidDto.setUid("test333");

        MvcResult checkResult = mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-up/check-id")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(checkUidDto))
                ).andExpect(status().isOk())
                .andReturn();

        String content1 = checkResult.getResponse().getContentAsString();
        ResVo resVo = mapper.readValue(content1, ResVo.class);
        assertEquals(1, resVo.getResult());

        UserSignUpDto signUpDto = new UserSignUpDto();
        signUpDto.setUid(checkUidDto.getUid());
        signUpDto.setUpw("test33##");
        signUpDto.setNm("테스트용");
        signUpDto.setZipCode("1123");
        signUpDto.setAddress("대구");
        signUpDto.setAddressDetail("그린컴퓨터");
        signUpDto.setEmail("test3@naver.com");
        signUpDto.setPhoneNumber("010-5555-5555");
        List<UserChildDto> children = new ArrayList<>();
        children.add(UserChildDto.builder()
                .gender("M")
                .ichildAge(2)
                .build());
        signUpDto.setChildren(children);

        MvcResult upResult = mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-up")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(signUpDto))
                ).andExpect(status().isOk())
                .andReturn();

        UserSignInDto signInDto = new UserSignInDto();
        signInDto.setUid(signUpDto.getUid());
        signInDto.setUpw(signUpDto.getUpw());

        MvcResult inResult = mvc.perform(
                        MockMvcRequestBuilders.post("/api/user/sign-in")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsBytes(signInDto))
                ).andExpect(status().isOk())
                .andReturn();

        String content = inResult.getResponse().getContentAsString();
        UserSignInVo vo = mapper.readValue(content, UserSignInVo.class);

        assertEquals(signUpDto.getNm(), vo.getNm());
    }
}
