package com.whataplabs.whatap.domain.product.acceptance.step;

import com.whataplabs.whatap.domain.product.ProductFixtures;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.dto.ProductUpdateRequest;
import com.whataplabs.whatap.global.acceptance.BaseAcceptanceTest;
import com.whataplabs.whatap.global.response.ResultResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;

import java.util.List;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static com.whataplabs.whatap.global.response.ResultCode.DELETE_PRODUCT_PAGE_SUCCESS;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Product 인수/통합 테스트")
public class ProductAcceptanceStep extends BaseAcceptanceTest {

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

  public static ExtractableResponse<Response> requestToUpdateProduct(
          ProductUpdateRequest productUpdateRequest) {
    return given()
            .log()
            .all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(productUpdateRequest)
            .when()
            .put("/api/v1/product")
            .then()
            .log()
            .all()
            .extract();
  }

  public static ExtractableResponse<Response> requestToGetOneProduct(Long id) {
    return given()
        .log()
        .all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .pathParam("id", id)
        .when()
        .get("/api/v1/product/{id}")
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> requestToGetProductWithPagination(
      int offset, int size) {
    return given()
        .log()
        .all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .param("id", offset)
        .param("size", size)
        .when()
        .get("/api/v1/product/page")
        .then()
        .log()
        .all()
        .extract();
  }

  public static ExtractableResponse<Response> requestToDeleteProduct(Long id) {
    return given()
            .log()
            .all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .pathParam("id", id)
            .when()
            .delete("/api/v1/product/{id}")
            .then()
            .log()
            .all()
            .extract();
  }

  public static void assertThatProductInfo(ExtractableResponse<Response> response) {
    ProductInfo expectedProductResponse = ProductFixtures.PRODUCT_ONE_INFO;
    ProductInfo actualResponse = response.body().jsonPath().getObject("data", ProductInfo.class);
    assertAll(() -> assertEquals(expectedProductResponse, actualResponse));
  }
  public static void assertThatUpdatedProductInfo(ExtractableResponse<Response> response) {
    ProductInfo expectedProductResponse = ProductFixtures.UPDATED_PRODUCT_ONE_INFO;
    ProductInfo actualResponse = response.body().jsonPath().getObject("data", ProductInfo.class);
    assertAll(() -> assertEquals(expectedProductResponse, actualResponse));
  }

  public static void assertThatProductWithPaginationInfo(ExtractableResponse<Response> response) {
    List<ProductInfo> productInfos =
        List.of(PRODUCT_ONE_INFO, PRODUCT_TWO_INFO, PRODUCT_THREE_INFO, PRODUCT_FOUR_INFO);
    ProductPageInfo expectedProductResponse =
        ProductPageInfo.builder().productInfos(productInfos).build();
    ProductPageInfo actualResponse =
        response.body().jsonPath().getObject("data", ProductPageInfo.class);
    System.out.println(actualResponse);

    assertAll(
        () ->
            assertEquals(
                expectedProductResponse.getProductInfos().get(0),
                actualResponse.getProductInfos().get(0)),
        () ->
            assertEquals(
                expectedProductResponse.getProductInfos().get(1),
                actualResponse.getProductInfos().get(1)),
        () ->
            assertEquals(
                expectedProductResponse.getProductInfos().get(2),
                actualResponse.getProductInfos().get(2)));
  }
  public static void assertThatDeleteProductById(ExtractableResponse<Response> response) {
    ResultResponse actualResponse = response.body().jsonPath().getObject(".", ResultResponse.class);
    assertAll(
            () -> assertEquals(DELETE_PRODUCT_PAGE_SUCCESS.getCode(), actualResponse.getCode()),
            () -> assertEquals(DELETE_PRODUCT_PAGE_SUCCESS.getMessage(), actualResponse.getMessage()),
            () -> assertEquals("", actualResponse.getData()));
  }
}
