package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.BoardGetVo;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.PageNation;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(SpringExtension.class) // 스프링 컨테이너 등록
@Import(BoardService.class) // BoardService 객체 주소 값을 얻음
class BoardServiceTest {
    int boardCode = 2;
    int page = 1;
    int amount = 10;

    @Autowired
    BoardService service;

    @MockBean
    private BoardMapper mapper;
    @MockBean
    private MyFileUtils myFileUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Test
    void getBoard() {
        BoardGetVo vo1 = BoardGetVo
                .builder()
                .iboard(1)
                .boardCode(boardCode)
                .title("더미 데이터1 (vo1)")
                .createdAt(String.valueOf(LocalDate.now()))
                .build();

        BoardGetVo vo2 = BoardGetVo
                .builder()
                .iboard(2)
                .boardCode(boardCode)
                .title("더미 데이터2 (vo2)")
                .createdAt(String.valueOf(LocalDate.now()))
                .build();

        List<BoardGetVo> list = new ArrayList<>();
        list.add(vo1);
        list.add(vo2);
        log.info("list = {}", list);

        // when에 있는 메소드의 return 값이 제대로 넘어오는지 확인 - 기능 확인용
        // 어디선가 getBoard를 호출하면 list를 반환할 수 있게 세팅
        when(mapper.getBoard(any())).thenReturn(list);

        PageNation.Criteria criteria = new PageNation.Criteria();
        criteria.setBoardCode(boardCode);
        criteria.setPage(page);
        criteria.setAmount(amount);

        List<BoardGetVo> result = mapper.getBoard(criteria);
        assertEquals(list, result);
        log.info("result = {}", result);
    }

    @Test
    void selBoard() {
    }
//
//    @Test
//    void createPics() {
//    }
//
//    @Test
//    void insBoard() {
//    }
//
//    @Test
//    void updBoard() {
//    }
//
//    @Test
//    void delBoard() {
//    }
}