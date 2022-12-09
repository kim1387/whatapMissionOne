package com.whataplabs.whatap.domain.product.dto.mapper;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ProductMapper {

  public Product mapProductRegisterToProductEntity(ProductRegisterRequest productRegisterRequest) {
    return Product.builder()
        .name(productRegisterRequest.getProductName())
        .introContent(productRegisterRequest.getProductIntro())
        .price(productRegisterRequest.getPrice())
        .build();
  }

  public ProductInfo mapProductEntityToProductInfo(Product product) {
    return ProductInfo.builder()
        .productName(product.getName())
        .productIntro(product.getIntroContent())
        .price(product.getPrice())
        .createdAt(product.getCreatedDate())
        .updatedAt(product.getUpdatedDate())
        .build();
  }
}
