package com.whataplabs.whatap.domain.product.service;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.dto.mapper.ProductMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @InjectMocks private ProductService productService;

  @Mock private ProductRepository productRepository;

  @Spy private ProductMapper productMapper;

  @Test
  @DisplayName("product 를 등록하는 메서드")
  void registerCityTest() {
    // given

    ProductRegisterRequest givenRequest = PRODUCT_ONE_REGISTER_REQUEST;
    Product expectReturnProduct = PRODUCT_ONE_ENTITY;
    ProductInfo expectedResponse = PRODUCT_ONE_INFO;

    // when
    when(productRepository.save(any())).thenReturn(expectReturnProduct);
    ProductInfo actualResponse = productService.registerProduct(givenRequest);
    // then
    assertAll(() -> assertEquals(expectedResponse, actualResponse));
  }
}
