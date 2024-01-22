package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.BoardCommentDelDto;
import com.baby.babycareproductsshop.board.model.BoardCommentGetVo;
import com.baby.babycareproductsshop.board.model.BoardCommentInsDto;
import com.baby.babycareproductsshop.board.model.BoardCommentUpdDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@MybatisTest // @Mapper 어노테이션이 등록된 인터페이스를 bean으로 등록
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // H2 off
class BoardCommentMapperTest {
    @Autowired
    private BoardCommentMapper mapper;
    private static BoardCommentInsDto insDto = new BoardCommentInsDto(); // @BeforeAll을 사용하기 위해 객체를 static으로 선언

    @BeforeAll
    public static void beforeAll() {
        int iboard = 275;
        int iuser = 15;
        String comment = "댓글 작성 테스트";

        insDto.setIboard(iboard);
        insDto.setIuser(iuser);
        insDto.setComment(comment);
    }

    @Test
    void getComment() {
        List<BoardCommentGetVo> getComment = mapper.getComment(1);
        assertEquals(3, getComment.size());
    }

    @Test
    void insComment() {
        int insComment = mapper.insComment(insDto);
        assertEquals(1, insComment);
    }

    @Test
    void delComment() {
        insComment();

        BoardCommentDelDto delDto = new BoardCommentDelDto(insDto.getIcomment(), insDto.getIuser());
        int delComment = mapper.delComment(delDto);
        assertEquals(1, delComment);
    }

    @Test
    void updComment() {
        insComment();

        BoardCommentUpdDto updDto = new BoardCommentUpdDto();
        updDto.setIcomment(insDto.getIcomment());
        updDto.setIuser(insDto.getIuser());
        updDto.setComment("댓글 수정 테스트입니다.");

        int updComment = mapper.updComment(updDto);
        assertEquals(1, updComment);
    }
}