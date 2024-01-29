package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderGetVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order_")
@Tag(name = "주문 API", description = "주문 관련 파트(이주영 전용 컨트롤러)")
public class OrderController_ {
    private final OrderService_ service;

    @GetMapping
    @Operation(summary = "주문 내역", description = "")
    List<OrderGetVo> getOrder(@RequestParam(name = "iorder") int iorder) {
        return service.getOrder(iorder);
    }

//    @GetMapping("/cancel")
//    @Operation(summary = "취소 내역", description = "")

}
