package com.whataplabs.whatap.domain.product.controller.document;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;

public class ProductRestDocument {
  public static RestDocumentationResultHandler getCreateProductInfoDocument() {
    return document(
        "/product/create",
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

  public static RestDocumentationResultHandler getGetOneProductInfoDocument() {
    return document(
        "/product/get",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        pathParameters(parameterWithName("id").description("조회할 product id")),
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

  public static RestDocumentationResultHandler getProductInfoWithPaginationDocument() {
    return document(
        "/product/get/page",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        requestParameters(
            parameterWithName("offset").description("pagination offset"),
            parameterWithName("size").description("page size")),
        responseFields(
            fieldWithPath("code").type(JsonFieldType.STRING).description("Business code"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("response message"),
            fieldWithPath("data.productInfos.[].productName")
                .type(JsonFieldType.STRING)
                .description("product 이름"),
            fieldWithPath("data.productInfos.[].productIntro")
                .type(JsonFieldType.STRING)
                .description("product 에 대한 소개"),
            fieldWithPath("data.productInfos.[].price")
                .type(JsonFieldType.NUMBER)
                .description("product 가격"),
            fieldWithPath("data.productInfos.[].createdAt")
                .type(JsonFieldType.STRING)
                .description("product 생성 일자"),
            fieldWithPath("data.productInfos.[].updatedAt")
                .type(JsonFieldType.STRING)
                .description("product 내용 수정 일자")));
  }

  public static RestDocumentationResultHandler getUpdateProductInfoDocument() {
    return document(
        "/product/update",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        requestFields(
            fieldWithPath("productId").type(JsonFieldType.NUMBER).description("수정할 product Id"),
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

  public static RestDocumentationResultHandler getDeleteProductDocument() {
    return document(
        "/product/delete",
        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
        pathParameters(parameterWithName("id").description("삭제할 Product id")),
        responseFields(
            fieldWithPath("code").type(JsonFieldType.STRING).description("Business code"),
            fieldWithPath("message").type(JsonFieldType.STRING).description("response message"),
            fieldWithPath("data").type(JsonFieldType.STRING).description("empty String")));
  }
}
