package com.whataplabs.whatap.domain.product.dto.mapper;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductPageInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
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

  public ProductPageInfo mapEntityToProductPageInfo(Page<Product> productList) {
    List<ProductInfo> productInfoList =
        productList.stream().map(this::mapProductEntityToProductInfo).collect(Collectors.toList());
    return ProductPageInfo.builder().productInfos(productInfoList).build();
  }
}
