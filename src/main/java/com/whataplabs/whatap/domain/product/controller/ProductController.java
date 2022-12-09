package com.whataplabs.whatap.domain.product.controller;

import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.service.ProductService;
import com.whataplabs.whatap.global.response.ResultCode;
import com.whataplabs.whatap.global.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
