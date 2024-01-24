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
                if(dto.getReqReviewPic() == null){
                    dto.setReqReviewPic(saveFileNm);
                }
            }
        }
        int result = mapper.insReviewPics(insDto);
        assertEquals(1, result);
    }

    @Test
    void getReview() {

        ReviewSelDto dto = new ReviewSelDto();
        dto.setIuser(authenticationFacade.getLoginUserPk());

        ReviewSelVo vo = new ReviewSelVo();
        vo.setIreview(75);
        vo.setContents("TDD1 Good");

        ReviewSelVo vo2 = new ReviewSelVo();
        vo.setIreview(75);
        vo2.setContents("TDD2 Good");

        List<ReviewSelVo> reviewSelVoList = new ArrayList<>();
        reviewSelVoList.add(vo);
        reviewSelVoList.add(vo2);

        when(mapper.getReview(any())).thenReturn(reviewSelVoList);

        String pics = "a.jpg";
        String pics2 = "b.jpg";

        ReviewPicsVo picsVo = new ReviewPicsVo(vo.getIreview(), pics);
        ReviewPicsVo picsVo2 = new ReviewPicsVo(vo2.getIreview(), pics2);

        List<ReviewPicsVo> picsVoList = new ArrayList<>();
        picsVoList.add(picsVo);
        picsVoList.add(picsVo2);

        List<Integer> integers = new ArrayList<>();
        integers.add(vo.getIreview());
        integers.add(vo2.getIreview());

        when(mapper.getReviewPics(integers)).thenReturn(picsVoList);

        assertEquals(2, reviewSelVoList.size());
        assertEquals(2,picsVoList.size());
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