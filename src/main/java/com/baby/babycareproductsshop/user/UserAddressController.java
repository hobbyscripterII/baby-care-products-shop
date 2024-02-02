package com.baby.babycareproductsshop.user;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.user.model.UserDelAddressDto;
import com.baby.babycareproductsshop.user.model.UserInsAddressDto;
import com.baby.babycareproductsshop.user.model.UserSelAddressVo;
import com.baby.babycareproductsshop.user.model.UserUpdAddressDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/address")
@Tag(name = "유저 주소 API", description = "유저 주소 관련 파트")
public class UserAddressController {
    private final UserService service;

    @Operation(summary = "주소 정보 입력", description = """
            입력 성공 -> result = 1<br>
            실패 실패 -> error
            """)
    @PostMapping
    public ResVo postUserAddress(@Valid  @RequestBody UserInsAddressDto dto){
        return service.postUserAddress(dto);
    }

    @Operation(summary = "주소 정보 조회", description = """
            """)
    @GetMapping
    public List<UserSelAddressVo> getUserAddress() {
        return service.getUserAddress();
    }

    @Operation(summary = "주소 정보 수정", description = """
            수정 성공 -> result = 1<br>
            수정 실패 -> error
            """)
    @PutMapping
    public ResVo putUserAddress(@Valid @RequestBody UserUpdAddressDto dto) {
        return service.putUserAddress(dto);
    }

    @Operation(summary = "주소 정보 삭제", description = """
            삭제 성공 -> result = 1<br>
            삭제 실패 -> error
            """)
    @DeleteMapping
    public ResVo delUserAddress(UserDelAddressDto dto) {
        return service.delUserAddress(dto);
    }
}
