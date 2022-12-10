package com.whataplabs.whatap.domain.product.acceptance;

import static com.whataplabs.whatap.domain.product.ProductFixtures.PRODUCT_ONE_REGISTER_REQUEST;
import static com.whataplabs.whatap.domain.product.acceptance.step.ProductAcceptanceStep.assertThatProductInfo;
import static com.whataplabs.whatap.domain.product.acceptance.step.ProductAcceptanceStep.requestToCreateProduct;
import static com.whataplabs.whatap.global.acceptance.step.AcceptanceStep.assertThatStatusIsOk;

import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.global.acceptance.BaseAcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}
