package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    List<BoardCommentGetVo> getComment(int iboard);
    int insComment(BoardCommentInsDto dto);
    int delComment(BoardCommentDelDto dto);
    int updComment(BoardCommentUpdDto dto);
}