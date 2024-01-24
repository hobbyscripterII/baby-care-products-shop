package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductContoller {
    private final ProductService service;
    //--------------------------------------검색기능---------------------------------------------
    /*@GetMapping("/search")
    @Operation(summary = "검색")
    @Parameters(value = {
            @Parameter(name = "String", description = "검색어")
            ,@Parameter(name = "minPrice", description = "최솟값")
            ,@Parameter(name = "maxPrice", description = "최댓값")
            ,@Parameter(name = "category", description = "카테고리")
            ,@Parameter(name = "sortBy", description = "정렬 값")
            ,@Parameter(name = "category", description = "카테고리")
    })
    public List<ProductSearchVo> searchProduct (ProductSearchPriceDto dto) {
        log.info("dto = {}",dto);
        return service.searchProductSelVo(dto);
    }*/

    //--------------------------------------메인 페이지---------------------------------------------
    @GetMapping()
    @Operation(summary = "메인화면")

    public List<ProductMainSelVo> getProduct (ProductMainSelDto dto) {
        return service.productMainSelVo2(dto);
    }

    //--------------------------------------월령별 상품 페이지---------------------------------------------

    @GetMapping("/productByAgeRange")
    @Operation(summary = "상품 조회 페이지")
    @Parameters(value = {
            @Parameter(name = "imain", description = "대분류 PK")
            ,@Parameter(name = "imiddle", description = "중분류 PK")
            ,@Parameter(name = "sortBy", description = "정렬 값")
    })
    public List<ProductMainSelVo> getProductByAgeRange(productByAgeRangeDto dto) {
        return service.getProductByAgeRange(dto);
    }

    //--------------------------------------상품 상세 보기---------------------------------------------
    @GetMapping("/{iproduct}")
    @Operation(summary = "상품상세정보")
    @Parameters(value = {
            @Parameter(name = "iproduct", description = "상품 PK")
    })
    public List<ProductSelVo> selProduct (@PathVariable int iproduct, ProductSelDto dto) {
        dto.setIproduct(iproduct);
        return service.selProduct(dto);
    }


    //--------------------------------------장바구니 조회---------------------------------------------
    @GetMapping("/selBasket")
    @Operation(summary = "장바구니 조회")
    public List<ProductBasketSelVo> selCartProduct (ProductBasketSelDto dto) {
        return service.productBasketSelVo(dto);
    }
    //--------------------------------------장바구니 상품 삭제---------------------------------------------
    @DeleteMapping()
    @Operation(summary = "장바구니물품삭제", description = "result : 성공 시 삭제 된 iproduct 개수 \n" +
            " , 실패 0  ")
    @Parameters(value = {
            @Parameter(name = "iproduct", description = "상품 PK")
    })
    public ResVo delCartProduct(@RequestBody List<Integer> iproduct) {
        return service.delBasket(iproduct);
    }
    //--------------------------------------장바구니 추가 ---------------------------------------------
    @PostMapping("/insBasket")
    @Operation(summary = "장바구니 추가", description = "result : 성공 시 해당 물품 수량 \n" +
            " , 실패 0  ")
    @Parameters(value = {
            @Parameter(name = "iproduct", description = "상품 PK"),
            @Parameter(name = "productCnt", description = "물품 수량")
    })
    public ResVo insCartProduct(@RequestBody ProductBasketInsDto dto) {
        return service.insBasket(dto);
    }
    //-------------------------------------------------------------------------------------------



    //--------------------------------------찜하기 기능---------------------------------------------
    @GetMapping("/wish")
    @Operation(summary = "찜하기기능",description = "찜하기기능")
    @Parameters(value = {
            @Parameter(name = "iproduct", description = "상품 PK"),
    })
    public ResVo wishProduct (ProductLikeDto dto) {
        log.info("dto = {}", dto);
        return service.wishProduct(dto);
    }
    //-------------------------------

}
