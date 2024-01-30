package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.*;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.security.AuthenticationFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping
    @Operation(summary = "게시글 목록 출력 기능", description = "")
    public List<BoardGetVo> getBoard(@RequestParam(name = "board_code") int boardCode, @RequestParam(name = "page") int page, @RequestParam(name = "keyword", required = false) String keyword) {
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
    public BoardSelVo selBoard(@PathVariable(name = "iboard") int iboard) {
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

    @PostMapping("/write")
    @Operation(summary = "게시글 등록 기능", description = "null로 게시글 등록 후 PK 반환(웹에디터 로직)")
    public int insBoard() {
        try {
            int iboard = service.insBoard();

            if (Utils.isNotNull(iboard)) {
                return iboard;
            }
            return Const.FAIL;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
        }
    }

    @PostMapping("/image-upload")
    @Operation(summary = "웹에디터 사진 등록 기능", description = "")
    public String imageUpload(@Parameter(description = "게시글 PK") @RequestParam(name = "iboard") int iboard, @RequestPart(name = "pics") MultipartFile pics) {
        try {
            String path = "/board/" + iboard;

            // db에 사진 저장 완료 시 사진 경로 문자열 반환 없으면 예외 던짐
            String savedFileName = myFileUtils.transferTo(pics, path);

            if (Utils.isNotNull(savedFileName)) {
                BoardPicsDto picsDto = new BoardPicsDto();
                picsDto.setIboard(iboard);
                picsDto.setPicName(savedFileName);
                int insBoardPic = service.insBoardPic(picsDto);

                if (Utils.isNotNull(insBoardPic)) {
                    return savedFileName;
                } else {
                    throw new RestApiException(AuthErrorCode.IMAGE_UPLOAD_FAIL);
                }
            } else {
                throw new RestApiException(AuthErrorCode.IMAGE_UPLOAD_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.IMAGE_UPLOAD_FAIL);
        }
    }

    @PutMapping
    @Operation(summary = "게시판 수정 기능", description = "")
    public ResVo updBoard(@Parameter(name = "dto") @RequestPart(name = "dto") BoardUpdDto dto, @Parameter(name = "pics") @RequestPart(name = "pics", required = false) List<MultipartFile> pics) {
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

    @DeleteMapping
    @Operation(summary = "게시판 삭제 기능", description = "")
    public ResVo delBoard(@RequestParam(name = "iboard") int iboard) {
        log.info("iboard = {}", iboard);

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