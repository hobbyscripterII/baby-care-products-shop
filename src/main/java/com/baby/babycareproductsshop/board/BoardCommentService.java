package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.baby.babycareproductsshop.common.Const.FAIL;
import static com.baby.babycareproductsshop.common.Const.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardCommentService {
    private final BoardCommentMapper mapper;
    private final AuthenticationFacade authenticationFacade;

    public List<BoardCommentGetVo> getComment(int iboard) {
        try {
            List<BoardCommentGetVo> list = mapper.getComment(iboard);

            if (Utils.isNotNull(list)) {
                return list;
            } else {
                throw new RestApiException(AuthErrorCode.COMMENT_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    public ResVo insComment(BoardCommentInsDto dto) {
        try {
            dto.setIuser(authenticationFacade.getLoginUserPk());
            int insCommentRows = mapper.insComment(dto);

            if (Utils.isNotNull(insCommentRows)) {
                return new ResVo(SUCCESS);
            } else {
                throw new RestApiException(AuthErrorCode.COMMENT_REGISTER_FAIL);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    public ResVo delComment(int icomment) {
        try {
            int loginUserPk = authenticationFacade.getLoginUserPk();
            int delCommentRows = mapper.delComment(new BoardCommentDelDto(icomment, loginUserPk));

            if (Utils.isNotNull(delCommentRows)) {
                return new ResVo(SUCCESS);
            } else {
                throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    public ResVo updComment(BoardCommentUpdDto dto) {
        try {
            dto.setIuser(authenticationFacade.getLoginUserPk());
            int updCommentRows = mapper.updComment(dto);

            if (Utils.isNotNull(updCommentRows)) {
                return new ResVo(SUCCESS);
            } else {
                throw new RestApiException(AuthErrorCode.COMMENT_REGISTER_FAIL);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
}