package com.whataplabs.whatap.domain.product.service;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.dto.ProductUpdateRequest;
import com.whataplabs.whatap.domain.product.dto.mapper.ProductMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
  @InjectMocks private ProductService productService;

  @Mock private ProductRepository productRepository;

  @Spy private ProductMapper productMapper;

  @Test
  @DisplayName("product 를 등록하는 메서드")
  void createProductTest() {
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

  @Test
  @DisplayName("product 단일 조회 메서드")
  void getProductByIdTest() {
    // given

    Optional<Product> expectReturnProduct = Optional.of(PRODUCT_ONE_ENTITY);
    ProductInfo expectedResponse = PRODUCT_ONE_INFO;

    // when
    when(productRepository.findProductsById(any())).thenReturn(expectReturnProduct);
    ProductInfo actualResponse = productService.getOneProduct(1L);
    // then
    assertAll(() -> assertEquals(expectedResponse, actualResponse));
  }

  @Test
  @DisplayName("product pagination 조회 메서드")
  void getProductWithPageTest() {
    // given
    List<ProductInfo> productInfos =
        List.of(PRODUCT_ONE_INFO, PRODUCT_TWO_INFO, PRODUCT_THREE_INFO, PRODUCT_FOUR_INFO);
    Page<Product> expectReturnProduct =
        new PageImpl<>(
            List.of(
                PRODUCT_ONE_ENTITY, PRODUCT_TWO_ENTITY, PRODUCT_THREE_ENTITY, PRODUCT_FOUR_ENTITY));

    ProductPageInfo expectedResponse = ProductPageInfo.builder().productInfos(productInfos).build();

    // when
    when(productRepository.findProductsWithPagination(any())).thenReturn(expectReturnProduct);
    ProductPageInfo actualResponse = productService.getProductByPagination(0, 3);
    // then
    assertAll(
        () ->
            assertEquals(
                expectedResponse.getProductInfos().get(0), actualResponse.getProductInfos().get(0)),
        () ->
            assertEquals(
                expectedResponse.getProductInfos().get(1), actualResponse.getProductInfos().get(1)),
        () ->
            assertEquals(
                expectedResponse.getProductInfos().get(2),
                actualResponse.getProductInfos().get(2)));
  }

  @Test
  @DisplayName("product 를 수정하는 메서드")
  void updateProductTest() {
    // given
    ProductUpdateRequest givenRequest = ProductUpdateRequest.builder().build();
    Product expectReturnProduct = PRODUCT_ONE_ENTITY;
    Product expectReturnUpdatedProduct = UPDATED_PRODUCT_ONE_ENTITY;
    ProductInfo expectedResponse = UPDATED_PRODUCT_ONE_INFO;

    // when
    when(productRepository.save(any())).thenReturn(expectReturnUpdatedProduct);
    when(productRepository.findProductsById(any())).thenReturn(Optional.of(expectReturnProduct));
    ProductInfo actualResponse = productService.updateProduct(givenRequest);
    // then
    assertAll(() -> assertEquals(expectedResponse, actualResponse));
  }

  @Test
  @DisplayName("product 를 삭제하는 메서드")
  void deleteProductTest() {
    // given
    Product expectReturnProduct = DELETABLE_PRODUCT_ONE_ENTITY;

    // when
    when(productRepository.findProductsById(any())).thenReturn(Optional.of(expectReturnProduct));
    productService.deleteProductById(1L);
    // then
    assertAll(() -> assertFalse(expectReturnProduct.isActivated()));
  }
}
