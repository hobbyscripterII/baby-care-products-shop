package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.PageNation;
import com.baby.babycareproductsshop.user.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardMapperTest {
    @Autowired
    private BoardMapper mapper;
    private static BoardInsDto insDto = new BoardInsDto(); // @BeforeAll을 사용하기 위해 객체를 static으로 선언

    @BeforeAll // 테스트 실행 전 딱 한번만 실행됨
    public static void beforeAll() {
        int iuser = 15;
        int boardCode = 2;
        String title = "브이콘 맛있네요.";
        String contents = "브이콘 먹는 중";

        insDto.setIuser(iuser);
      //  insDto.setBoardCode(boardCode);
      //  insDto.setTitle(title);
      //  insDto.setContents(contents);
    }

    @Test
    @DisplayName("게시글 등록")
    void insBoard() {
        int insBoard = mapper.insBoard(insDto);
        assertEquals(1, insBoard);
    }

    @Test
    @DisplayName("게시글 수정")
    void updBoard() {
        insBoard();

        BoardUpdDto updDto = new BoardUpdDto();
        updDto.setIboard(insDto.getIboard());
        updDto.setIuser(insDto.getIuser());
        updDto.setTitle("게시글 수정 테스트");
        updDto.setContents("게시글 수정 테스트 중입니다.");

        int updBoard = mapper.updBoard(updDto);
        assertEquals(1, updBoard);
    }

    @Test
    @DisplayName("게시글 삭제")
    void delBoard() {
        insBoard();

        BoardDelDto delDto = new BoardDelDto(insDto.getIboard(), insDto.getIuser());
        int delBoard = mapper.delBoard(delDto);
        assertEquals(1, delBoard);
    }

    @Test
    @DisplayName("게시글 읽기")
    void selBoard() {
        BoardSelVo setBoard = mapper.selBoard(275);
        assertNotNull(setBoard);
    }

    @Test
    @DisplayName("게시글 목록 가져오기")
    void getBoard() {
        PageNation.Criteria criteria = new PageNation.Criteria();
        criteria.setBoardCode(1);
        criteria.setKeyword("게시글 작성 테스트");
        criteria.setPage(1);
        criteria.setAmount(10);
        List<BoardGetVo> getBoard = mapper.getBoard(criteria);
    }

    @Test
    @DisplayName("게시글 사진 등록")
    void insBoardPics() {
    }
}