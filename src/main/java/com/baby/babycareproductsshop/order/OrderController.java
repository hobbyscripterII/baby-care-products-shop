package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.order.model.OrderInsDto;
import com.baby.babycareproductsshop.order.model.OrderInsVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
@Tag(name = "주문 API", description = "주문 관련 파트")
public class OrderController {
    private final OrderService service;

    @PostMapping
    public OrderInsVo postOrder(@RequestBody OrderInsDto dto){
        return service.postOrder(dto);
    }

    @PutMapping
    public String confirmOrder() {
        return null;
    }
}
