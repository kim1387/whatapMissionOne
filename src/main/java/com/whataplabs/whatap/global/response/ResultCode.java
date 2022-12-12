package com.whataplabs.whatap.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Enum Naming Format : {행위}_{목적어}_{성공여부} message format: 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ResultCode {

  // product
  CREATE_PRODUCT_SUCCESS("P001", "Product 생성 성공"),
  GET_ONE_PRODUCT_SUCCESS("P002", "Product 단일 조회 성공"),
  GET_PRODUCT_PAGE_SUCCESS("P003", "Product Pagination 조회 성공"),
  DELETE_PRODUCT_PAGE_SUCCESS("P004", "Product 삭제 성공"),
  ;

  private final String code;
  private final String message;
}
