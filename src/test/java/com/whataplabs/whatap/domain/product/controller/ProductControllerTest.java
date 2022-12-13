package com.whataplabs.whatap.domain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.whataplabs.whatap.domain.product.ProductFixtures;
import com.whataplabs.whatap.domain.product.controller.document.ProductRestDocument;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.whataplabs.whatap.domain.product.ProductFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {
  @MockBean private ProductService productService;

  private ObjectMapper objectMapper;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp(
      WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentationContextProvider) {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    mockMvc =
        MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
            .apply(documentationConfiguration(restDocumentationContextProvider))
            .build();
  }

  @Test
  @DisplayName("product 등록 api")
  void registerProduct() throws Exception {
    // given
    ProductInfo productInfo = ProductFixtures.PRODUCT_ONE_INFO;
    ProductRegisterRequest cityRegisterRequest = PRODUCT_ONE_REGISTER_REQUEST;
    // when
    when(productService.registerProduct(any())).thenReturn(productInfo);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cityRegisterRequest)))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(ProductRestDocument.getCreateProductInfoDocument());
  }

  @Test
  @DisplayName("product 단위 조회 api")
  void getOneProduct() throws Exception {
    // given
    ProductInfo productInfo = ProductFixtures.PRODUCT_ONE_INFO;
    // when
    when(productService.getOneProduct(any())).thenReturn(productInfo);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/v1/product/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(ProductRestDocument.getGetOneProductInfoDocument());
  }

  @Test
  @DisplayName("product pagination 조회 api")
  void getProductWithPagination() throws Exception {
    // given
    List<ProductInfo> productInfos =
        List.of(PRODUCT_ONE_INFO, PRODUCT_TWO_INFO, PRODUCT_THREE_INFO, PRODUCT_FOUR_INFO);
    ProductPageInfo expectedResponse = ProductPageInfo.builder().productInfos(productInfos).build();

    // when
    when(productService.getProductByPagination(anyInt(), anyInt())).thenReturn(expectedResponse);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/api/v1/product/page")
                .contentType(MediaType.APPLICATION_JSON)
                .param("offset", "0")
                .param("size", "3"))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(ProductRestDocument.getProductInfoWithPaginationDocument());
  }

  @Test
  @DisplayName("product 수정 api")
  void updateProduct() throws Exception {
    // given
    // when
    when(productService.updateProduct(any())).thenReturn(UPDATED_PRODUCT_ONE_INFO);

    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.put("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_ONE_UPDATE_REQUEST)))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(ProductRestDocument.getUpdateProductInfoDocument());
  }

  @Test
  @DisplayName("product 삭제 api")
  void deleteProduct() throws Exception {
    // given
    // when
    doNothing().when(productService).deleteProductById(any());
    // then
    mockMvc
        .perform(
            RestDocumentationRequestBuilders.delete("/api/v1/product/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andDo(ProductRestDocument.getDeleteProductDocument());
  }
}
