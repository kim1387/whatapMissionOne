package com.whataplabs.whatap.domain.product.controller;

import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.service.ProductService;
import com.whataplabs.whatap.global.response.ResultCode;
import com.whataplabs.whatap.global.response.ResultResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ResultResponse> registerProduct(
      @Valid @RequestBody ProductRegisterRequest request) {
    ProductInfo productInfo = productService.registerProduct(request);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_PRODUCT_SUCCESS, productInfo));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResultResponse> getOneProduct(@PathVariable Long id) {
    ProductInfo productInfo = productService.getOneProduct(id);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_ONE_PRODUCT_SUCCESS, productInfo));
  }

  @GetMapping("/page")
  public ResponseEntity<ResultResponse> getProductByPagination(
      @RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "10") int size) {
    ProductPageInfo productInfo = productService.getProductByPagination(offset, size);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_ONE_PRODUCT_SUCCESS, productInfo));
  }
}
