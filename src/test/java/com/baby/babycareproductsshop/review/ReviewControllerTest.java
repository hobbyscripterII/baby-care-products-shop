package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.review.model.ReviewInsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;

@WebMvcTest({ReviewController.class})
class ReviewControllerTest {

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper om;

    @MockBean private ReviewService service;

    @Test
    void insReview() throws Exception {
        ResVo vo = new ResVo(1);
        given(service.insReview(any())).willReturn(vo);

        ReviewInsDto dto = new ReviewInsDto();
        dto.setIproduct(5);
        String json = om.writeValueAsString(dto);
        System.out.println("json = " + json);

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(om.writeValueAsString(vo)))
                .andDo(print());
                verify(service).insReview(any());
    }

    @Test
    void getReview() {
    }

    @Test
    void delReview() {
    }
}