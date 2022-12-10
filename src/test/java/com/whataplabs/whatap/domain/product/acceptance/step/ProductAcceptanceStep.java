package com.whataplabs.whatap.domain.product.acceptance.step;

import com.whataplabs.whatap.domain.product.ProductFixtures;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductAcceptanceStep {

  public static ExtractableResponse<Response> requestToCreateProduct(
      ProductRegisterRequest productRegisterRequest) {
    return given()
        .log()
        .all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(productRegisterRequest)
        .when()
        .post("/api/v1/product")
        .then()
        .log()
        .all()
        .extract();
  }

  public static void assertThatProductInfo(ExtractableResponse<Response> response) {
    ProductInfo expectedProductResponse = ProductFixtures.PRODUCT_ONE_INFO;
    ProductInfo actualResponse = response.body().jsonPath().getObject("data", ProductInfo.class);
    System.out.println(actualResponse);
    assertAll(() -> assertEquals(expectedProductResponse, actualResponse));
  }


}
