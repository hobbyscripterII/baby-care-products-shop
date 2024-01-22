package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.review.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewMapperTest {

    @Autowired private ReviewMapper mapper;

    @DisplayName("리뷰 작성 테스트")
    @Test
    void insReview() {
        List<MultipartFile> list = new ArrayList<>();
        final String fileName = "testImage1";
        final String contentType = ".jpg";
        final String filePath = "review/" + fileName + contentType;

        MultipartFile multipartFile1 =
                new MockMultipartFile(fileName, contentType, filePath, "test file".getBytes(StandardCharsets.UTF_8) );
        MultipartFile multipartFile2 =
                new MockMultipartFile(fileName, contentType, filePath, "test file2".getBytes(StandardCharsets.UTF_8) );

        list.add(multipartFile1);
        list.add(multipartFile2);

        ReviewInsDto dto = new ReviewInsDto();
        dto.setIreview(1);
        dto.setIorder(1);
        dto.setIproduct(1);
        dto.setIuser(1);
        dto.setContents("공백");
        dto.setProductScore(1);
        dto.setPics(list);
        int insReview = mapper.insReview(dto);
        assertEquals(1, insReview);

    }

    @Test
    void insReviewPics() {
        List<String> list = new ArrayList<>();
        String str = "한글.jpg";
        String str2 = "영어.jpg";
        list.add(str);
        list.add(str2);

        ReviewPicsInsDto insDto = new ReviewPicsInsDto();
        insDto.setIreview(75);
        insDto.setPics(list);

        int insReviewPics = mapper.insReviewPics(insDto);
        assertEquals(2, insReviewPics);

    }

    @DisplayName("리뷰 목록 테스트")
    @Test
    void getReview() {
        ReviewSelVo vo = new ReviewSelVo(1, "이름", 1, "내용", "날짜", null);
        ReviewSelVo vo2 = new ReviewSelVo(2, "이름2", 2, "내용2", "날짜2", null);

        ReviewSelDto selDto = new ReviewSelDto();
        selDto.setIuser(19);
        selDto.setPage(1);

        List<ReviewSelVo> selVos = mapper.getReview(selDto);
        selVos.add(vo);
        selVos.add(vo2);
        assertEquals(19, selDto.getIuser());
        assertNotNull(selVos);
    }

    @Test
    void getReviewPics() {
        ReviewPicsVo vo = new ReviewPicsVo(1, "pics.jpg");
        ReviewPicsVo vo2 = new ReviewPicsVo(2, "pics2.jpg");

        List<Integer> ireview = new ArrayList<>();
        ireview.add(vo.getIreview());
        ireview.add(vo2.getIreview());
        List<ReviewPicsVo> picsVos = mapper.getReviewPics(ireview);
        picsVos.add(vo);
        picsVos.add(vo2);
        assertEquals(2, ireview);
        assertNotNull(picsVos);
    }

    @Test
    void selReviewByReview() {
        ReviewDelDto dto = new ReviewDelDto();
        dto.setIreview(75);
        dto.setIuser(19);

        int selReviewByReview = mapper.selReviewByReview(dto);
        assertEquals(1, selReviewByReview);

        ReviewDelDto dto2 = new ReviewDelDto();
        dto2.setIreview(15);
        dto2.setIuser(1);

        int selReviewByReview2 = mapper.selReviewByReview(dto);
        assertEquals(1, selReviewByReview2);
    }

    @Test
    void delReviewByReview() {
        ReviewDelDto dto = new ReviewDelDto();
        dto.setIreview(75);
        dto.setIuser(19);

        int delReviewByPics = mapper.delReviewByPics(dto);
        assertEquals(1, delReviewByPics);

        ReviewDelDto dto2 = new ReviewDelDto();
        dto2.setIreview(17);
        dto2.setIuser(3);

        int selReviewByReview2 = mapper.selReviewByReview(dto);
        assertEquals(1, selReviewByReview2);
    }

    @Test
    void delReview() {
        ReviewDelDto dto = new ReviewDelDto();
        dto.setIreview(75);
        dto.setIuser(19);

        int delReview = mapper.delReview(dto);
        assertEquals(1, delReview);

        ReviewDelDto dto2 = new ReviewDelDto();
        dto2.setIreview(19);
        dto2.setIuser(5);

        int selReviewByReview2 = mapper.selReviewByReview(dto);
        assertEquals(1, selReviewByReview2);
    }
}