package com.whataplabs.whatap.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@RequiredArgsConstructor
public class ProductUpdateRequest {

  @NotNull(message = "상품 Id를 입력해주세요.")
  private final Long productId;
  
  @NotBlank(message = "상품이름을 입력해주세요.")
  private final String productName;

  @NotBlank(message = "상품의 간단한 소개를 입력해주세요.")
  private final String productIntro;

  @Min(value = 100, message = "상품의 가격은 100원 이상이어야합니다")
  @NotNull(message = "상품의 가격을 입력해주세요.")
  private final Integer price;
}
