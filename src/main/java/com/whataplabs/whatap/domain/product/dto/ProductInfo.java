package com.whataplabs.whatap.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductInfo {

  private String name;

  private String introContent;

  private Integer price;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder
  public ProductInfo(
      String name,
      String introContent,
      Integer price,
      LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.name = name;
    this.introContent = introContent;
    this.price = price;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
