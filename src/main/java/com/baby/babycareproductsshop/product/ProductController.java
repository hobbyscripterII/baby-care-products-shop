package com.baby.babycareproductsshop.product;

import com.baby.babycareproductsshop.common.ResVo;
import com.baby.babycareproductsshop.product.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "상품 API", description = "상품 관련 파트")
public class ProductController {
    private final ProductService service;

    //--------------------------------------검색기능---------------------------------------------
    @PostMapping("/search")
    @Operation(summary = "검색")
    public List<ProductSearchVo> searchProduct(@RequestBody ProductSearchDto dto) {
        return service.searchProductSelVo(dto);
    }

    //--------------------------------------메인 페이지---------------------------------------------
    @GetMapping("/main")
    @Operation(summary = "메인화면 비로그인")
    public List<ProductMainSelVo> getNonLoginProduct() { // 비로그인
        return service.productMainSelVo();
    }

    //--------------------------------------메인 페이지---------------------------------------------
    @GetMapping("/login-main")
    @Operation(summary = "메인화면 로그인")
    public List<ProductMainSelVo> getLoginProduct() { // 로그인
        return service.productMainLoginSelVo();
    }

    //--------------------------------------메인 페이지---------------------------------------------
    @GetMapping("/pop-new-product")
    @Operation(summary = "인기 신상품 상품 조회")
    public List<ProductMainSelVo> getPopAndNewProduct() { //인기상품 & 신상품
        return service.productPopNewSelVo();
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
    public ProductSelVo getSelProduct(@PathVariable int iproduct, ProductSelDto dto) {
        dto.setIproduct(iproduct);
        return service.selProduct(dto);
    }

    //--------------------------------------장바구니 조회---------------------------------------------
    @GetMapping("/cart")
    @Operation(summary = "장바구니 조회")
    public List<ProductBasketSelVo> selCartProduct() {
        return service.productBasketSelVo();
    }

    //--------------------------------------장바구니 상품 삭제---------------------------------------------
    @DeleteMapping("/cart")
    @Operation(summary = "장바구니물품삭제", description = "result : 성공 시 삭제 된 iproduct 개수 \n" +
            " , 실패 0  ")
    public ResVo delCartProduct(@RequestParam List<Integer> iproduct) {
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
    @PatchMapping("/cart")
    @Operation(summary = "장바구니 물품 수량 변경", description = "result : 성공 시 해당 물품 수량 \n" +
            " , 실패 0  ")
    public ResVo uptBasket(ProductBasketInsDto dto) {
        return service.uptBasket(dto);
    }

    //--------------------------------------찜하기 기능---------------------------------------------
    @PutMapping("/wish")
    @Operation(summary = "찜하기기능", description = "찜하기기능")
    public ResVo wishProduct(@RequestBody ProductLikeDto dto) {
        log.info("dto = {}", dto);
        return service.wishProduct(dto);
    }
    //-------------------------------

    @PostMapping
    @Operation(summary = "상품 등록 기능")
    public ResVo postProduct(@RequestPart(name = "pics") List<MultipartFile> pics,
                             @RequestPart(name = "productDetails") MultipartFile productDetails,
                             @RequestPart ProductInsDto dto) {
        log.info("dto : {}", dto);
        log.info("pics : {}", pics);
        log.info("productDetails : {}", productDetails);
        return service.postProduct(pics, productDetails, dto);
    }
}
