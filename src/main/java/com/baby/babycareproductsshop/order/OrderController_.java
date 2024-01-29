package com.baby.babycareproductsshop.order;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.order.model.OrderGetDto;
import com.baby.babycareproductsshop.order.model.OrderGetListVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    List<OrderGetListVo> getOrder(@Parameter(description = "1 - 주문내역 조회 2 - 주문취소/반품내역 조회")
                                  @RequestParam(name = "list_flag") int listFlag) {
        OrderGetDto dto = new OrderGetDto();
        dto.setIorder(1); // 수정 필요 - 전체 다 불러올껀지 조건문 줄껀지
        dto.setListFl(listFlag);
        return service.getOrder(dto);
    }

    @DeleteMapping
    @Operation(summary = "주문 취소", description = "")
    public ResVo cancelOrder(@Parameter(description = "주문 PK") @RequestParam(name = "iorder") int iorder) {
        try {
            return service.orderCancel(iorder);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
