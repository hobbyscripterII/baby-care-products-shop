package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.user.model.*;
import com.baby.babycareproductsshop.validation.ValidationSequence;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 API", description = "유저 관련 파트")
public class UserController {
    private final UserService service;

    @Operation(summary = "회원가입 약관 조회")
    @GetMapping("/sign-up")
    public List<UserClauseVo> getClause() {
        return service.getClause();
    }

    @Operation(summary = "회원 가입", description = """
            result = userId pk값 : 회원가입 성공<br>
            error : 회원가입 실패
            """)
    @PostMapping("/sign-up")
    public ResVo postSignUp(@Valid @RequestBody UserSignUpDto dto) {
        return service.postSignUp(dto);
    }

    @Operation(summary = "회원 가입 시 닉네임 중복 확인 기능", description = """
            중복되는 닉네임 없음 -> result = 1<br>
            중복되는 닉네임 있음 -> error
            """)
    @PostMapping("/sign-up/check-id")
    public ResVo postCheckUid(@Valid @RequestBody UserCheckUidDto dto) {
        log.info("uid : {}",dto);
        return service.postCheckUid(dto);
    }

    @Operation(summary = "로그인", description = """
            로그인 성공 -> result = 1<br>
            로그인 실패 -> error
            """)
    @PostMapping("/sign-in")
    public UserSignInVo postSignIn(HttpServletResponse res,@Valid @RequestBody UserSignInDto dto) {
        return service.postSignIn(res, dto);
    }

    @Operation(summary = "내 정보 불러오기 기능", description = """
                nm : 유저 이름
                beforeDeposit : 입금 전
                preparation : 배송 준비중
                shipping : 배송중
                completed : 배송 완료
                myWishList : 찜한 상품 목록
            """)
    @GetMapping("/my-page")
    public UserSelMyInfoVo getMyInfo() {
        return service.getMyInfo();
    }


    @Operation(summary = "회원 정보 수정 전 비밀번호 확인 기능", description = """
            비밀번호 일치 -> result = 1<br>
            비밀번호 불일치 -> error 코드 응답
            """)
    @PostMapping("/modify")
    public UserSelToModifyVo postCheckUpw(@Valid @RequestBody UserCheckUpwDto dto) {
        return service.postCheckUpw(dto);
    }

    @Operation(summary = "회원 정보 수정", description = """
            수정 성공 -> result = 1<br>
            수정 실패 -> error
            """)
    @PutMapping("/modify")
    public ResVo putUserInfo(@Valid @RequestBody UserUpdDto dto) {
        return service.putUserInfo(dto);
    }

    @Operation(summary = "회원 탈퇴", description = """
            탈퇴 성공 -> result = 1<br>
            탈퇴 실패 -> error
            """)
    @DeleteMapping("/modify")
    public ResVo unregister() {
        return service.unregister();
    }

    @Operation(summary = "로그 아웃", description = """
            성공 -> result = 1
            """)
    @PostMapping("/signout")
    public ResVo postSignout(HttpServletRequest req, HttpServletResponse res) {
        return service.signout(res);
    }

    @Operation(summary = "access token 재발급", description = """
            """)
    @GetMapping("/refresh-token")
    public UserSignInVo getRefreshToken(HttpServletRequest req) {
        return service.getRefreshToken(req);
    }
}
