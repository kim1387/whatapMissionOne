package com.whataplabs.whatap.domain.product.service;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public ProductInfo registerProduct(ProductRegisterRequest productRegisterRequest) {
    Product newProduct =
        Product.builder()
            .name(productRegisterRequest.getProductName())
            .introContent(productRegisterRequest.getProductIntro())
            .price(productRegisterRequest.getPrice())
            .build();
    Product savedProduct = productRepository.save(newProduct);

    return ProductInfo.builder()
        .name(savedProduct.getName())
        .introContent(savedProduct.getIntroContent())
        .price(savedProduct.getPrice())
        .createdAt(savedProduct.getCreatedDate())
        .updatedAt(savedProduct.getUpdatedDate())
        .build();
  }
}
