package com.whataplabs.whatap.domain.product;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import com.whataplabs.whatap.domain.product.dto.ProductInfo;
import com.whataplabs.whatap.domain.product.dto.ProductRegisterRequest;
import java.time.LocalDateTime;

public class ProductFixtures {

  public static final Product PRODUCT_ONE_ENTITY =
      Product.builder().name("상품1").introContent("상품1 소개").price(1000).build();
  public static final Product PRODUCT_TWO_ENTITY =
      Product.builder().name("상품2").introContent("상품2 소개").price(2000).build();
  public static final Product PRODUCT_THREE_ENTITY =
      Product.builder().name("상품3").introContent("상품3 소개").price(3000).build();
  public static final Product PRODUCT_FOUR_ENTITY =
      Product.builder().name("상품4").introContent("상품4 소개").price(4000).build();

  public static final ProductRegisterRequest PRODUCT_ONE_REGISTER_REQUEST =
      ProductRegisterRequest.builder()
          .productName("상품1")
          .productIntro("상품1 소개")
          .price(1000)
          .build();

  public static final ProductInfo PRODUCT_ONE_INFO =
      ProductInfo.builder()
          .productName("상품1")
          .productIntro("상품1 소개")
          .price(1000)
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();

  public static final ProductInfo PRODUCT_TWO_INFO =
      ProductInfo.builder()
          .productName("상품2")
          .productIntro("상품2 소개")
          .price(2000)
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();

  public static final ProductInfo PRODUCT_THREE_INFO =
      ProductInfo.builder()
          .productName("상품3")
          .productIntro("상품3 소개")
          .price(3000)
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();

  public static final ProductInfo PRODUCT_FOUR_INFO =
      ProductInfo.builder()
          .productName("상품4")
          .productIntro("상품4 소개")
          .price(4000)
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();
}
