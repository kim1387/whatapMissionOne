package com.whataplabs.whatap.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProductInfo that = (ProductInfo) o;
    return Objects.equals(name, that.name)
        && Objects.equals(introContent, that.introContent)
        && Objects.equals(price, that.price)
        && Objects.equals(createdAt, that.createdAt)
        && Objects.equals(updatedAt, that.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, introContent, price, createdAt, updatedAt);
  }
}
