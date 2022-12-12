package com.whataplabs.whatap.domain.product.acceptance;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.global.acceptance.BaseAcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static com.whataplabs.whatap.domain.product.acceptance.step.ProductAcceptanceStep.*;
import static com.whataplabs.whatap.global.acceptance.step.AcceptanceStep.assertThatStatusIsOk;

@DisplayName("Product 인수/통합 테스트")
class ProductAcceptanceTest extends BaseAcceptanceTest {

  @Autowired ProductRepository productRepository;

  @DisplayName("Product 를 생성한다.")
  @Test
  void createProductTest() {
    // given
    ProductRegisterRequest givenRequest = PRODUCT_ONE_REGISTER_REQUEST;

    // when
    ExtractableResponse<Response> response = requestToCreateProduct(givenRequest);

    // then
    assertThatStatusIsOk(response);
    assertThatProductInfo(response);
  }

  @DisplayName("Product 를 단일 조회.")
  @Test
  void getOneProductTest() {
    // given
    Product saveProduct = productRepository.save(PRODUCT_ONE_ENTITY);

    // when
    ExtractableResponse<Response> response = requestToGetOneProduct(saveProduct.getId());

    // then
    assertThatStatusIsOk(response);
    assertThatProductInfo(response);
  }

  @DisplayName("Product 를 pagination 조회.")
  @Test
  void getProductWithPaginationTest() {
    // given
    productRepository.saveAll(
        List.of(PRODUCT_ONE_ENTITY, PRODUCT_TWO_ENTITY, PRODUCT_THREE_ENTITY, PRODUCT_FOUR_ENTITY));
    // when
    ExtractableResponse<Response> response = requestToGetProductWithPagination(0, 3);

    // then
    assertThatStatusIsOk(response);
    assertThatProductWithPaginationInfo(response);
  }
}
