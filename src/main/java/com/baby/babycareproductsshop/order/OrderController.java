package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.common.Utils;
import com.baby.babycareproductsshop.exception.AuthErrorCode;
import com.baby.babycareproductsshop.exception.RestApiException;
import com.baby.babycareproductsshop.order.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "주문 API", description = "주문 관련 파트")
public class OrderController {
    private final OrderService service;

    @Operation(summary = "상품 구매", description = """
            상품 구매를 누르면 사용되는 요청<br>
            성공 : result = iorder(주문 pk)
            """)
    @PostMapping
    public ResVo postOrder(@RequestBody @Valid OrderInsDto dto) {
        return service.postOrder(dto);
    }

    @Operation(summary = "주문 및 결제 페이지 요청")
    @GetMapping("/confirm")
    public OrderInsVo getOrderForConfirm(@RequestParam int iorder) {
        return service.getOrderForConfirm(iorder);
    }

    @Operation(summary = "주문 완료 페이지 요청", description = """
            주문 및 결제에서 주문하기 를 누르면 사용되는 요청
            """)
    @PutMapping
    public ResVo putConfirmOrder(@RequestBody @Valid OrderConfirmDto dto) {
        return service.putConfirmOrder(dto);
    }

    @Operation(summary = "주문 상세 내역 확인")
    @GetMapping("/{iorder}")
    public OrderConfirmVo getOrderDetails(@PathVariable int iorder) {
        return service.getOrderDetails(iorder);
    }

    @Operation(summary = "상품 환불 처리")
    @PostMapping("/{idetails}")
    public ResVo refundOrder(@PathVariable int idetails,
                             @RequestBody @Valid OrderInsRefundDto dto) {
        dto.setIdetails(idetails);
        return service.refundOrder(dto);
    }

    @GetMapping
    @Operation(summary = "주문 내역", description = "")
    List<OrderGetListVo> getOrder(@Parameter(description = "1 - 주문내역 조회 2 - 주문취소/반품내역 조회")
                                  @RequestParam(name = "list_flag") Integer listFlag,
                                  @Parameter(description = "1 - 입금전 2 - 배송준비중 3 - 배송중 4 - 배송완료 5 - 환불/취소")
                                  @RequestParam(name = "process_state_code", required = false) Integer processStateCode,
                                  @RequestParam(name = "date", required = false) Integer date) {

        OrderGetListDto dto = new OrderGetListDto();
        dto.setListFl(listFlag);

        if (processStateCode != null) {
            dto.setProcessStateCode(processStateCode);
        } else if (date != null) {
            dto.setDate(date);
        }

        List<OrderGetListVo> list = service.getOrder(dto);

        if (Utils.isNotNull(list)) {
            return list;
        } else if (!Utils.isNotNull(list.size())) {
            throw new RestApiException(AuthErrorCode.NOT_FOUND_ORDER_LIST);
        } else {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }

    @DeleteMapping
    @Operation(summary = "주문 취소", description = "")
    public ResVo cancelOrder(@Parameter(description = "주문 PK") @RequestParam(name = "iorder") int iorder) {
        try {
            return service.orderCancel(iorder);
        } catch (Exception e) {
            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
        }
    }
}
