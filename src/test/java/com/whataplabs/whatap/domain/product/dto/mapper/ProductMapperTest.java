package com.whataplabs.whatap.domain.product.dto.mapper;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

  private ProductMapper productMapper;

  @BeforeEach
  public void setUp() {
    this.productMapper = new ProductMapper();
  }

  @Test
  @DisplayName("Product Register Request 를 Entity 로 반환하는 method test")
  void mapProductRegisterToProductEntityTest() {
    // given
    Product expectedProduct = PRODUCT_ONE_ENTITY;
    // when
    Product actualProduct =
        productMapper.mapProductRegisterToProductEntity(PRODUCT_ONE_REGISTER_REQUEST);
    // then
    assertAll(
        () -> assertEquals(expectedProduct.getName(), actualProduct.getName()),
        () -> assertEquals(expectedProduct.getIntroContent(), actualProduct.getIntroContent()),
        () -> assertEquals(expectedProduct.getPrice(), actualProduct.getPrice()));
  }

  @Test
  @DisplayName("Product Entity 를 ProductInfo Response 로 반환하는 method test")
  void mapProductEntityToProductInfo() {
    // given
    ProductInfo expectedProductInfo = PRODUCT_ONE_INFO;
    // when
    ProductInfo actualProductInfo = productMapper.mapProductEntityToProductInfo(PRODUCT_ONE_ENTITY);
    // then
    assertAll(
        () -> assertEquals(expectedProductInfo.getName(), actualProductInfo.getName()),
        () ->
            assertEquals(
                expectedProductInfo.getIntroContent(), actualProductInfo.getIntroContent()),
        () -> assertEquals(expectedProductInfo.getPrice(), actualProductInfo.getPrice()));
  }
}
