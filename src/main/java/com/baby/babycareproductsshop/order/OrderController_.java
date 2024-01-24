package com.baby.babycareproductsshop.order;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order_")
@Tag(name = "주문 API", description = "주문 관련 파트(이주영 전용 컨트롤러)")
public class OrderController_ {
    private final OrderService_ service;


}
