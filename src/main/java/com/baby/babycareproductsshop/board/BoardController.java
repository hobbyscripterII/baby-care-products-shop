package com.baby.babycareproductsshop.board;

import com.baby.babycareproductsshop.board.model.*;
import com.baby.babycareproductsshop.common.PageNation;
import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
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

    @GetMapping
    @Operation(summary = "게시글 목록 출력 기능", description = "")
    public List<BoardGetVo> getBoard(@RequestParam(name = "board_code") int boardCode, @RequestParam int page, @RequestParam(required = false) String keyword) {
        try {
            if (Utils.isNotNull(boardCode) && Utils.isNotNull(page)) {
                PageNation.Criteria criteria = new PageNation.Criteria();
                criteria.setPage(page);
                criteria.setBoardCode(boardCode);
                criteria.setKeyword(keyword);

                List<BoardGetVo> list = service.getBoard(criteria);
//                PageNation pageNation = new PageNation(criteria, list.size());
                return list;
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

    @PostMapping
    @Operation(summary = "게시글 등록 기능", description = "")
    public ResVo insBoard(@RequestPart BoardInsDto dto, @RequestPart(required = false) List<MultipartFile> pics) {
        try {
            if (!Utils.isNotNull(dto)) {
                throw new RestApiException(AuthErrorCode.POST_REGISTER_FAIL);
            } else {
                if (pics != null) {
                    dto.setPics(pics);
                }
                return service.insBoard(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @PatchMapping
    @Operation(summary = "게시판 수정 기능", description = "")
    public ResVo updBoard(@RequestPart BoardUpdDto dto, @RequestPart List<MultipartFile> pics) {
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