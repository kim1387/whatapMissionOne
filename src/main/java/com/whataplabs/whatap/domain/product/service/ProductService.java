package com.whataplabs.whatap.domain.product.service;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.dto.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  public ProductInfo registerProduct(ProductRegisterRequest productRegisterRequest) {
    Product newProduct = productMapper.mapProductRegisterToProductEntity(productRegisterRequest);
    Product savedProduct = productRepository.save(newProduct);
    return productMapper.mapProductEntityToProductInfo(savedProduct);
  }
}
