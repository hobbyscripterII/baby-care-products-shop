package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
@Tag(name = "상품 API", description = "상품 관련 파트")
public class ProductController {
    private final ProductService service;
    //--------------------------------------검색기능---------------------------------------------
    @PostMapping("/search")
    @Operation(summary = "검색")
    public List<ProductSearchVo> searchProduct (@RequestBody ProductSearchDto dto) {
        log.info("dto = {}",dto);
        return service.searchProductSelVo(dto);
    }

    //--------------------------------------메인 페이지---------------------------------------------
    @GetMapping("main")
    @Operation(summary = "메인화면")

    public List<ProductMainSelVo> getProduct ( ) {
        return service.productMainSelVo();
    }

    //--------------------------------------월령별 상품 페이지---------------------------------------------

    @GetMapping
    @Operation(summary = "상품 조회 페이지")

    public List<ProductListSelVo> getProductList(ProductListDto dto) {
        return service.getProductByAgeRange(dto);
    }

    //--------------------------------------상품 상세 보기---------------------------------------------
    @GetMapping("/{iproduct}")
    @Operation(summary = "상품상세정보")

    public List<ProductSelVo> selProduct (@PathVariable int iproduct, ProductSelDto dto) {
        dto.setIproduct(iproduct);
        return service.selProduct(dto);
    }

    //--------------------------------------장바구니 조회---------------------------------------------
    @GetMapping("/cart")
    @Operation(summary = "장바구니 조회")
    public List<ProductBasketSelVo> SelCartProduct (ProductBasketSelDto dto) {
        return service.productBasketSelVo(dto);
    }
    //--------------------------------------장바구니 상품 삭제---------------------------------------------
    @DeleteMapping("/cart")
    @Operation(summary = "장바구니물품삭제", description = "result : 성공 시 삭제 된 iproduct 개수 \n" +
            " , 실패 0  ")

    public ResVo delCartProduct(@RequestBody List<Integer> iproduct) {
        return service.delBasket(iproduct);
    }
    //--------------------------------------장바구니 추가 ---------------------------------------------
    @PostMapping("/cart")
    @Operation(summary = "장바구니 추가", description = "result : 성공 시 해당 물품 수량 \n" +
            " , 실패 0  ")

    public ResVo insCartProduct(@RequestBody ProductBasketInsDto dto) {
        return service.insBasket(dto);
    }
    //-------------------------------------------------------------------------------------------



    //--------------------------------------찜하기 기능---------------------------------------------
    @GetMapping("/wish")
    @Operation(summary = "찜하기기능",description = "찜하기기능")

    public ResVo wishProduct (ProductLikeDto dto) {
        log.info("dto = {}", dto);
        return service.wishProduct(dto);
    }
    //-------------------------------

}
