package com.whataplabs.whatap.domain.product.controller.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;

public class ProductRestDocument {
  public static RestDocumentationResultHandler getCreateProductInfoDocument() {
    return document(
        "/travel/create",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        requestFields(
            fieldWithPath("productName").type(JsonFieldType.STRING).description("product 이름"),
            fieldWithPath("productIntro").type(JsonFieldType.STRING).description("product 소개"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("product 가격")),
        responseFields(
            fieldWithPath("code").type(JsonFieldType.STRING).description("Business code"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("response message"),
            fieldWithPath("data.productName").type(JsonFieldType.STRING).description("product 이름"),
            fieldWithPath("data.productIntro")
                .type(JsonFieldType.STRING)
                .description("product 에 대한 소개"),
            fieldWithPath("data.price").type(JsonFieldType.NUMBER).description("product 가격"),
            fieldWithPath("data.createdAt").type(JsonFieldType.STRING).description("product 생성 일자"),
            fieldWithPath("data.updatedAt")
                .type(JsonFieldType.STRING)
                .description("product 내용 수정 일자")));
  }
}
