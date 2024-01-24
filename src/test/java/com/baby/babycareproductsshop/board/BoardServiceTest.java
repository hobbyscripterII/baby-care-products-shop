package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.Const;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.PageNation;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(SpringExtension.class) // 스프링 컨테이너 등록
@Import(BoardService.class)        // BoardService 객체 주소 값을 얻음
class BoardServiceTest {
    private int boardCode = 2;
    private int page = 1;
    private int amount = 10;
    private int iboard = 1;
    private int iuser = 1;
    private String nm = "주영";
    private String createdAt = String.valueOf(LocalDate.now());

    @Autowired
    BoardService service;

    @MockBean
    private BoardMapper mapper;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    AuthenticationFacade authenticationFacade;

    @Test
    @DisplayName("게시글 가져오기")
    void getBoard() {
        BoardGetVo vo1 = BoardGetVo
                .builder()
                .iboard(1)
                .boardCode(boardCode)
                .title("더미 데이터1 = vo1")
                .createdAt(createdAt)
                .build();

        BoardGetVo vo2 = BoardGetVo
                .builder()
                .iboard(2)
                .boardCode(boardCode)
                .title("더미 데이터2 = vo2")
                .createdAt(createdAt)
                .build();

        List<BoardGetVo> list = new ArrayList<>();
        list.add(vo1);
        list.add(vo2);

        // given - 특정 메소드 호출 시 테스트를 위한 상황을 설정할 수 있음
        given(mapper.getBoard(any(PageNation.Criteria.class))).willReturn(list);

        // when - 특정 메소드 호출 시 return 값을 지정할 수 있음
        //        기능 확인용으로 사용되며 테스트 코드 내에서 해당 메소드 호출 시 return 값이 반환됨
        when(mapper.getBoard(any())).thenReturn(list);

        PageNation.Criteria criteria = new PageNation.Criteria();
        criteria.setBoardCode(boardCode);
        criteria.setPage(page);
        criteria.setAmount(amount);

        List<BoardGetVo> result = service.getBoard(criteria);

        // then - 앞선 과정의 결과
        assertEquals(list, result);
    }

    @Test
    @DisplayName("게시글 읽기")
    void selBoard() {
        BoardSelVo selVo = new BoardSelVo();
        selVo.setIboard(iboard);
        selVo.setIuser(iuser);
        selVo.setTitle("게시글 읽기 테스트");
        selVo.setContents("게시글 읽기 테스트 중 입니다.");
        selVo.setNm(nm);
        selVo.setCreatedAt(createdAt);

        given(mapper.selBoard(selVo.getIboard())).willReturn(selVo);
        when(mapper.selBoard(anyInt())).thenReturn(selVo);

        BoardSelVo result = service.selBoard(iboard);

        assertEquals(selVo, result);
    }

    @Test
    @DisplayName("게시글 사진 등록")
    void createPics() {
        // public MockMultipartFile(String name, @Nullable String originalFilename, @Nullable String contentType, @Nullable byte[] content)
        MockMultipartFile testImg1 = new MockMultipartFile("테스트용 이미지1", "test-img1.jpg", MediaType.IMAGE_JPEG_VALUE, "test-img1".getBytes());
        // public MockMultipartFile(String name, @Nullable byte[] content)
        MockMultipartFile testImg2 = new MockMultipartFile("테스트용 이미지2", "test-img2".getBytes());

        List<MultipartFile> pics = new ArrayList<>();
        pics.add(testImg1);
        pics.add(testImg2);

        List<String> picsNames = new ArrayList<>();
        picsNames.add(testImg1.getName());
        picsNames.add(testImg2.getName());

        BoardPicsDto picsDto = new BoardPicsDto();
        picsDto.setIboard(iboard);
        //picsDto.setBoardCode(boardCode);
        picsDto.setPics(pics);
        picsDto.setPicNames(picsNames);

        given(service.createPics(anyInt(), any())).willReturn(picsDto);
        when(service.createPics(anyInt(), any())).thenReturn(picsDto);

        BoardPicsDto createPics = service.createPics(iboard, pics);

        assertEquals(service.createPics(anyInt(), any()), createPics);
    }

    @Test
    @DisplayName("게시글 등록")
    void insBoard() throws Exception {
        MockMultipartFile testImg1 = new MockMultipartFile("테스트용 이미지1", "test-img1.jpg", MediaType.IMAGE_JPEG_VALUE, "test-img1".getBytes());
        MockMultipartFile testImg2 = new MockMultipartFile("테스트용 이미지2", "test-img2".getBytes());

        List<MultipartFile> list = new ArrayList<>();

        list.add(testImg1);
        list.add(testImg2);

        BoardInsDto insDto = new BoardInsDto();
        insDto.setIboard(iboard);
        insDto.setIuser(iuser);
        insDto.setBoardCode(boardCode);
        insDto.setTitle("게시글 등록 테스트");
        insDto.setContents("게시글 등록 테스트입니다.");
        insDto.setPics(list);

        given(mapper.insBoard(insDto)).willReturn(1);
        when(mapper.insBoard(insDto)).thenReturn(1);

        //ResVo insBoard = service.insBoard(insDto);

        //assertEquals(1, insBoard.getResult());
    }

    @Test
    @DisplayName("게시글 수정")
    void updBoard() throws Exception {
        String title = "게시글 수정 테스트";
        String contents = "게시글 수정 테스트입니다.";
        ResVo success = new ResVo(Const.SUCCESS);

        BoardUpdDto trueUser = new BoardUpdDto();
        trueUser.setIboard(iboard);
        trueUser.setIuser(iuser);
        trueUser.setTitle(title);
        trueUser.setContents(contents);

        BoardUpdDto falseUser = new BoardUpdDto();
        falseUser.setIboard(iboard);
        falseUser.setIuser(2);
        falseUser.setTitle(title);
        falseUser.setContents(contents);

        given(service.updBoard(trueUser)).willReturn(success); // given
        when(service.updBoard(trueUser)).thenReturn(success); // when
        ResVo updBoardTrue = service.updBoard(trueUser);
        assertEquals(success, updBoardTrue); // then

        ResVo updBoardFalse = service.updBoard(falseUser); // null
        assertNotEquals(success, updBoardFalse);
    }

    @Test
    @DisplayName("게시글 삭제")
    void delBoard() {
        BoardDelDto delDto1 = new BoardDelDto(iboard, iuser);
        BoardDelDto delDto2 = new BoardDelDto(iboard, 2);


    }
}