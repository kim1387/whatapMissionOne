package com.whataplabs.whatap.domain.product.domain.repository;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.exception.NotFoundProductException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ProductRepositoryTest {

  @Autowired private ProductRepository productRepository;

  @Test
  @DisplayName("product 저장 함수 test")
  void saveCityTest() {
    // given

    // when
    Product actualProduct = productRepository.save(PRODUCT_ONE_ENTITY);
    Product expectedProduct = PRODUCT_ONE_ENTITY;

    // then
    assertAll(
        () -> assertEquals(expectedProduct.getName(), actualProduct.getName()),
        () -> assertEquals(expectedProduct.getIntroContent(), actualProduct.getIntroContent()),
        () -> assertEquals(expectedProduct.getPrice(), actualProduct.getPrice()));
  }

  @Nested
  @DisplayName("product 단일 조회")
  class GetOneProductsByIdTest {

    @Test
    @DisplayName("product 단일 조회 성공")
    void getOneProductsByIdSuccessTest() {
      // given
      Product givenProduct = productRepository.save(PRODUCT_ONE_ENTITY);

      // when
      Product actualProduct =
          productRepository
              .findProductsById(givenProduct.getId())
              .orElseThrow(NotFoundProductException::new);

      // then
      assertAll(() -> assertEquals(givenProduct, actualProduct));
    }

    @Test
    @DisplayName("예외: 존재 하지 않는 id로 product 단일 조회")
    void getOneProductsByIdExceptionTest() {
      // given
      // not given anything

      // when, then
      assertAll(() -> assertEquals(Optional.empty(), productRepository.findProductsById(10000L)));
    }
  }

  @Nested
  @DisplayName("product 단일 조회")
  class GetProductsWithPagiNationTest {

    @Test
    @DisplayName("product Pagination 조회 성공")
    void getProductsWithPaginationSuccessTest() {
      // given
      List<Product> productList =
          List.of(
              PRODUCT_ONE_ENTITY, PRODUCT_TWO_ENTITY, PRODUCT_THREE_ENTITY, PRODUCT_FOUR_ENTITY);
      List<Product> givenProductList = productRepository.saveAll(productList);
      PageRequest pageRequest = PageRequest.of(0, 3);
      // when
      List<Product> productsWithPagination =
          productRepository.findProductsWithPagination(pageRequest).getContent();

      assertAll(
          () -> assertEquals(3, productsWithPagination.size()),
          () -> assertEquals(givenProductList.get(0), productsWithPagination.get(0)),
          () -> assertEquals(givenProductList.get(1), productsWithPagination.get(1)),
          () -> assertEquals(givenProductList.get(2), productsWithPagination.get(2)));
    }
  }
}
