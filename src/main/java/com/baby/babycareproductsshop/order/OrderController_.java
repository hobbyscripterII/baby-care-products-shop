//package com.baby.babycareproductsshop.order;
//
//import com.baby.babycareproductsshop.common.ResVo;
//import com.baby.babycareproductsshop.common.Utils;
//import com.baby.babycareproductsshop.exception.AuthErrorCode;
//import com.baby.babycareproductsshop.exception.RestApiException;
//import com.baby.babycareproductsshop.order.model.*;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/order_")
//@Tag(name = "주문 API", description = "주문 관련 파트")
//public class OrderController_ {
//    private final OrderService_ service;
//
//    @GetMapping
//    @Operation(summary = "주문 내역", description = "")
//    List<OrderGetListVo> getOrder(@Parameter(description = "1 - 주문내역 조회 2 - 주문취소/반품내역 조회")
//                                  @RequestParam(name = "list_flag") Integer listFlag,
//                                  @Parameter(description = "1 - 입금전 2 - 배송준비중 3 - 배송중 4 - 배송완료 5 - 환불/취소")
//                                  @RequestParam(name = "process_state_code", required = false) Integer processStateCode,
//                                  @RequestParam(name = "date", required = false) Integer date) {
//
//        OrderGetListDto dto = new OrderGetListDto();
//        dto.setListFl(listFlag);
//
//        if (processStateCode != null) {
//            dto.setProcessStateCode(processStateCode);
//        } else if (date != null) {
//            dto.setDate(date);
//        }
//
//        List<OrderGetListVo> list = service.getOrder(dto);
//
//        if (Utils.isNotNull(list)) {
//            return list;
//        } else if (!Utils.isNotNull(list.size())) {
//            throw new RestApiException(AuthErrorCode.NOT_FOUND_ORDER_LIST);
//        } else {
//            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
//        }
//    }
//
//    @DeleteMapping
//    @Operation(summary = "주문 취소", description = "")
//    public ResVo cancelOrder(@Parameter(description = "주문 PK") @RequestParam(name = "iorder") int iorder) {
//        try {
//            return service.orderCancel(iorder);
//        } catch (Exception e) {
//            throw new RestApiException(AuthErrorCode.GLOBAL_EXCEPTION);
//        }
//    }
//}
