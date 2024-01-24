package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.MyFileUtils;
import com.baby.babycareproductsshop.common.PageNation;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Tag(name = "게시판 API", description = "게시판 관련 파트")
public class BoardController {
    private final BoardService service;
    private final MyFileUtils myFileUtils;
    private final AuthenticationFacade authenticationFacade;
    private int iboard;

    @GetMapping("/pagenation")
    @Operation(summary = "게시글 페이지네이션 기능", description = "[보류] 담당자랑 협의 후 수정 진행")
    public PageNation getPageNation(@RequestParam(name = "board_code") int boardCode, @RequestParam int page, @RequestParam(required = false) String keyword) {
        PageNation.Criteria criteria = new PageNation.Criteria();
        criteria.setPage(page);
        criteria.setBoardCode(boardCode);
        criteria.setKeyword(keyword);
        List<BoardGetVo> list = service.getBoard(criteria);

        return new PageNation(criteria, list.size());
    }

    @GetMapping
    @Operation(summary = "게시글 목록 출력 기능", description = "")
    public List<BoardGetVo> getBoard(@RequestParam(name = "board_code") int boardCode, @RequestParam int page, @RequestParam(required = false) String keyword) {
        try {
            if (Utils.isNotNull(boardCode) && Utils.isNotNull(page)) {
                PageNation.Criteria criteria = new PageNation.Criteria();
                criteria.setPage(page);
                criteria.setBoardCode(boardCode);
                criteria.setKeyword(keyword);
                return service.getBoard(criteria);
            } else {
                throw new RestApiException(AuthErrorCode.POST_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @GetMapping("/{iboard}")
    @Operation(summary = "게시글 읽기 기능", description = "")
    public BoardSelVo selBoard(@PathVariable int iboard) {
        try {
            if (Utils.isNotNull(iboard)) {
                return service.selBoard(iboard);
            } else {
                throw new RestApiException(AuthErrorCode.POST_NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @PostMapping("/webeditor")
    @Operation(summary = "웹에디터 사진 등록 기능", description = "")
    public String insBoardPics(@RequestPart(name = "pic") MultipartFile pic) {
        try {
            // null로 게시글 insert(pk 값 받아오기 위함)
            BoardInsDto insDto = new BoardInsDto();
            insDto.setIuser(authenticationFacade.getLoginUserPk());
            int insBoardRows = service.insBoard(insDto);

            // null로 insert 완료 시 해당 pk로 폴더 생성 후 이미지 업로드 진행
            if (Utils.isNotNull(insBoardRows)) {
                this.iboard = insDto.getIboard();
                log.info("this.iboard = {}", this.iboard);
                String path = "/board/" + insDto.getIboard();
                log.info("path = {}", path);
                String savedFileName = myFileUtils.transferTo(pic, path);
                log.info("savedFileName = {}", savedFileName);

                // 이미지 업로드 완료 시 tbl에 사진 경로 저장
                if (Utils.isNotNull(savedFileName)) {
                    BoardPicsDto picsDto = new BoardPicsDto();
                    picsDto.setIboard(insDto.getIboard());
                    picsDto.setPicName(savedFileName);
                    int insBoardPic = service.insBoardPic(picsDto);
                    log.info("insBoardPic = {}", insBoardPic);

                    // db에 사진 저장 완료 시 사진 경로 문자열 반환 없으면 null로 반환
                    return Utils.isNotNull(insBoardPic) ? savedFileName : null;
                }
            }
            throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

//    @GetMapping
//    @Operation(summary = "게시글 등록 기능", description = "")
//    public ResVo insBoard() {
//        return null;
//    }

    @PostMapping
    @Operation(summary = "게시글 등록 기능", description = "")
    public ResVo insBoard(@RequestPart BoardInsDto dto, @RequestPart(required = false) List<MultipartFile> pics) {
        // iboard 있으면 update 진행 없으면 insert
        try {
            if (!Utils.isNotNull(dto)) {
                throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
            } else {
                if (pics != null) {
                    dto.setIboard(iboard);
                    dto.setPics(pics);
                }

                BoardUpdDto updDto = new BoardUpdDto();
                updDto.setIboard(iboard);
                updDto.setIuser(authenticationFacade.getLoginUserPk());
                updDto.setTitle(dto.getTitle());
                updDto.setContents(dto.getContents());
                return service.updBoard(updDto);
//                return service.insBoard(dto);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @PatchMapping
    @Operation(summary = "게시판 수정 기능", description = "")
    public ResVo updBoard(@RequestPart BoardUpdDto dto, @RequestPart(required = false) List<MultipartFile> pics) {
        try {
            if (!Utils.isNotNull(dto)) {
                throw new RestApiException(AuthErrorCode.POST_DELETE_FAIL);
            } else {
                if (pics != null) {
                    dto.setPics(pics);
                }
                return service.updBoard(dto);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
        }
    }

    @DeleteMapping("{iboard}")
    @Operation(summary = "게시판 삭제 기능", description = "")
    public ResVo delBoard(@PathVariable int iboard) {
        try {
            if (Utils.isNotNull(iboard)) {
                return service.delBoard(iboard);
            } else {
                throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
            }
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
}