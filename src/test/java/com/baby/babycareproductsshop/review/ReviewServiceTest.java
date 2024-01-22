package com.baby.babycareproductsshop.review;

import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.review.model.*;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.util.*;

@ExtendWith(SpringExtension.class)
@Import({ReviewService.class})
class ReviewServiceTest {


    @MockBean ReviewMapper mapper;
    @MockBean MyFileUtils myFileUtils;
    @MockBean AuthenticationFacade authenticationFacade;

    @Autowired ReviewService service;

    @Test
    void insReview() {

        when(mapper.insReview(any())).thenReturn(1);
        when(mapper.insReviewPics(any())).thenReturn(1);

        final String fileName = "testImage1";
        final String contentType = ".jpg";
        final String filePath = "review/" + fileName + contentType;

        List<MultipartFile> files = new ArrayList<>();
        MultipartFile multipartFile1 =
                new MockMultipartFile(fileName, contentType, filePath, "test file".getBytes(StandardCharsets.UTF_8) );
        MultipartFile multipartFile2 =
                new MockMultipartFile(fileName, contentType, filePath, "test file2".getBytes(StandardCharsets.UTF_8) );
        files.add(multipartFile1);
        files.add(multipartFile2);

        ReviewInsDto dto = new ReviewInsDto();
        dto.setIreview(1);
        dto.setIuser(authenticationFacade.getLoginUserPk());
        dto.setPics(files);
        ResVo vo = service.insReview(dto);
        assertEquals(dto.getIreview(), vo.getResult());

        verify(mapper).insReview(any());
        verify(mapper).insReviewPics(any());

        ReviewPicsInsDto insDto = new ReviewPicsInsDto();
        insDto.setIreview(dto.getIreview());
        String target = "review/" + dto.getIreview();

        if(dto.getPics() != null){
            for(MultipartFile file : dto.getPics()){
                String saveFileNm = myFileUtils.transferTo(file, target);
                insDto.getPics().add(saveFileNm);
            }
        }
        int result = mapper.insReviewPics(insDto);
        assertEquals(1, result);
    }

    @Test
    void getReview() {

        ReviewSelDto dto = new ReviewSelDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());

        List<ReviewSelVo> result = mapper.getReview(dto);

        List<ReviewSelVo> reviewSelVoList = new ArrayList<>();
        ReviewSelVo vo = new ReviewSelVo();
        vo.setContents("TDD 작업");
        ReviewSelVo vo2 = new ReviewSelVo();
        vo2.setContents("TDD 작업");
        reviewSelVoList.add(vo);
        reviewSelVoList.add(vo2);

        List<Integer> iReviewList = new ArrayList<>();
        iReviewList.add(vo.getIreview());
        iReviewList.add(vo2.getIreview());

        Map<Integer, ReviewSelVo> reviewSelVoMap = new HashMap<>();
        for(ReviewSelVo selVo : reviewSelVoList){
            iReviewList.add(selVo.getIreview());
            reviewSelVoMap.put(selVo.getIreview(), selVo);
        }
        if(iReviewList.size() > 0){
            List<ReviewPicsVo> reviewPicsVoList = mapper.getReviewPics(iReviewList);
            for(ReviewPicsVo picsVo : reviewPicsVoList){
                ReviewSelVo reviewSelVo = reviewSelVoMap.get(picsVo.getIreview());
                List<String> pics = reviewSelVo.getPics();
                pics.add(picsVo.getReviewPic());
            }
        }
        assertEquals(result, reviewSelVoList);
    }

    @Test
    void delReview() {
        when(mapper.selReviewByReview(any())).thenReturn(1);
        when(mapper.delReviewByPics(any())).thenReturn(3);
        when(mapper.delReview(any())).thenReturn(5);

        ReviewDelDto dto = new ReviewDelDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());


        int selReview = mapper.selReviewByReview(any());
        if(selReview == 0){
            ResVo vo1 = new ResVo(0);
        }
        if( selReview == 1){
            mapper.delReviewByPics(any());
            mapper.delReview(any());
        }
        int result = mapper.selReviewByReview(dto);
        assertEquals(result, selReview);
    }
}