package com.whataplabs.whatap.domain.product.service;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.domain.repository.ProductRepository;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import com.whataplabs.whatap.domain.product.dto.ProductUpdateRequest;
import com.whataplabs.whatap.domain.product.dto.mapper.ProductMapper;
import com.whataplabs.whatap.domain.product.exception.NotFoundProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional(readOnly = true)
  public ProductInfo getOneProduct(Long id) {
    Product foundProduct =
        productRepository.findProductsById(id).orElseThrow(NotFoundProductException::new);
    return productMapper.mapProductEntityToProductInfo(foundProduct);
  }

  @Transactional(readOnly = true)
  public ProductPageInfo getProductByPagination(int offset, int size) {
    PageRequest pageRequest = PageRequest.of(offset, size);
    Page<Product> productByPagination = productRepository.findProductsWithPagination(pageRequest);
    return productMapper.mapEntityToProductPageInfo(productByPagination);
  }

  public ProductInfo updateProduct(ProductUpdateRequest request) {
    Product foundProduct =
        productRepository
            .findProductsById(request.getProductId())
            .orElseThrow(NotFoundProductException::new);

    foundProduct.update(request);
    Product updatedProduct = productRepository.save(foundProduct);

    return productMapper.mapProductEntityToProductInfo(updatedProduct);
  }

  public void deleteProductById(Long id) {
    Product foundProduct =
        productRepository.findProductsById(id).orElseThrow(NotFoundProductException::new);
    foundProduct.delete();
  }
}
