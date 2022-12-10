package com.whataplabs.whatap.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Enum Naming Format : {행위}_{목적어}_{성공여부} message format: 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ResultCode {

  // product
  CREATE_PRODUCT_SUCCESS("P001", "Product 생성 성공"),
  ;

  private final String code;
  private final String message;
}
