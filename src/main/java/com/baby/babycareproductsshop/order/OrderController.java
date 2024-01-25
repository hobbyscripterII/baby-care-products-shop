package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.OrderConfirmDto;
import com.baby.babycareproductsshop.order.model.OrderConfirmVo;
import com.baby.babycareproductsshop.order.model.OrderInsDto;
import com.baby.babycareproductsshop.order.model.OrderInsVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "주문 API", description = "주문 관련 파트")
public class OrderController {
    private final OrderService service;

    @Operation(summary = "주문 결제 페이지 요청", description = """
            상품 구매를 누르면 사용되는 요청
            """)
    @PostMapping
    public OrderInsVo postOrder(@RequestBody OrderInsDto dto){
        return service.postOrder(dto);
    }

    @Operation(summary = "주문 완료 페이지 요청", description = """
            주문 및 결제에서 주문하기 를 누르면 사용되는 요청
            """)
    @PutMapping
    public OrderConfirmVo confirmOrder(@RequestBody OrderConfirmDto dto) {
        return service.confirmOrder(dto);
    }

    @Operation(summary = "주문 상세 내역 확인")
    @GetMapping("/{iorder}")
    public OrderConfirmVo getOrderDetails(@PathVariable int iorder) {
        return service.getOrderDetails(iorder);
    }

    @Operation(summary = "상품 환불 처리")
    @DeleteMapping("/{idetails}")
    public ResVo refundOrder(@PathVariable int idetails) {
        return null;
    }

}
