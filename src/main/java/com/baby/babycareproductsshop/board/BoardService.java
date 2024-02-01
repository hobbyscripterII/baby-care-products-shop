package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.*;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.baby.babycareproductsshop.common.Const.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;

    public List<BoardGetVo> getBoard(PageNation.Criteria criteria) {
        try {
            List<BoardGetVo> list = mapper.getBoard(criteria);

            if (Utils.isNotNull(list)) {
                return list;
            } else {
                throw new RestApiException(AuthErrorCode.POST_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    public BoardSelVo selBoard(int iboard) {
        try {
            BoardSelVo vo = mapper.selBoard(iboard);

            if (Utils.isNotNull(vo)) {
                return vo;
            } else {
                throw new RestApiException(AuthErrorCode.POST_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    public BoardPicsDto createPics(int iboard, List<MultipartFile> pics) {
        try {
            BoardPicsDto dto = new BoardPicsDto();
            dto.setIboard(iboard);
            dto.setPics(pics);

            String path = "/board/" + dto.getIboard();
            myFileUtils.delDirTrigger(path);

            for (MultipartFile pic : dto.getPics()) {
                String savedFileName = myFileUtils.transferTo(pic, path);
                dto.getPicNames().add(savedFileName);
            }

            return dto;
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.PICS_CREATE_FAIL);
        }
    }

    @Transactional
    public int insBoard() {
        try {
            int loginUserPk = authenticationFacade.getLoginUserPk();
            BoardInsDto dto = new BoardInsDto();
            dto.setIuser(loginUserPk);
            int insBoardRows = mapper.insBoard(dto);

            if (Utils.isNotNull(insBoardRows)) {
                return dto.getIboard();
            }

            return Const.FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
        }
    }

    @Transactional
    public int insBoardPic(BoardPicsDto dto) {
        return mapper.insBoardPic(dto);
    }

    @Transactional
    public ResVo updBoard(BoardUpdDto dto) {
        try {
            log.info("dto = {}", dto);
            int loginUserPk = authenticationFacade.getLoginUserPk();
            dto.setIuser(loginUserPk);
            int updBoardRows = mapper.updBoard(dto);
            log.info("updBoardRows = {}", updBoardRows);

            // 작성자 외 다른 사용자가 접근했을 때 및 게시글 수정 실패 시
            if (!Utils.isNotNull(updBoardRows)) {
                throw new RestApiException(AuthErrorCode.USER_MODIFY_FAIL);

                // 등록 시 사진이 있었으나 수정 시 사진이 없을 때 테이블 사진, 디렉토리 모두 삭제
            } else if (Utils.isNotNull(updBoardRows) && dto.getPics() == null) {
                String path = "/board/" + dto.getIboard();
                myFileUtils.delDirTrigger(path);
                int delBoardPicsRows = mapper.delBoardPics(dto.getIboard());

                if (Utils.isNotNull(delBoardPicsRows)) {
                    return new ResVo(SUCCESS);
                } else {
                    throw new RestApiException(AuthErrorCode.POST_DELETE_FAIL);
                }
            } else {
                BoardPicsDto picsDto = createPics(dto.getIboard(), dto.getPics());
                int insBoardPicsRows = mapper.insBoardPics(picsDto);

                if (dto.getPics().size() == insBoardPicsRows) {
                    return new ResVo(SUCCESS);
                } else {
                    throw new RestApiException(AuthErrorCode.POST_DELETE_FAIL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @Transactional
    public ResVo delBoard(int iboard) {
        try {
            int loginUserPk = authenticationFacade.getLoginUserPk();
            int delBoardRows = mapper.delBoard(new BoardDelDto(iboard, loginUserPk));

            if (Utils.isNotNull(delBoardRows)) {
                myFileUtils.delDirTrigger("/board/" + iboard);
                return new ResVo(SUCCESS);
            } else {
                throw new RestApiException(AuthErrorCode.POST_DELETE_FAIL);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
}