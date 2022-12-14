package com.whataplabs.whatap.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Enum Naming Format : {주체}_{이유} message format: 동사 명사형으로 마무리 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
  // Global
  INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
  INPUT_INVALID_VALUE(400, "G002", "잘못된 입력"),

  // Test
  TEST_EXCEPTION_VALUE(400, "T001", "예외 테스트 출력"),

  // Product
  NOT_FOUND_PRODUCT_ENTITY(400, "P001", "존재하지 않는 product 입니다."),
  ;

  private final int status;
  private final String code;
  private final String message;
}
