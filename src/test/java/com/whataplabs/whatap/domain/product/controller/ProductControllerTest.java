package com.whataplabs.whatap.domain.product.controller;

import static com.whataplabs.whatap.domain.product.ProductFixtures.PRODUCT_ONE_REGISTER_REQUEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.whataplabs.whatap.domain.product.ProductFixtures;
import com.whataplabs.whatap.domain.product.controller.document.ProductRestDocument;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.service.ProductService;
import java.nio.charset.StandardCharsets;
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
  void registerCity() throws Exception {
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
}
