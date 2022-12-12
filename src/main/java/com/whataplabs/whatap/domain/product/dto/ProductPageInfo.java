package com.whataplabs.whatap.domain.product.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ProductPageInfo {

  private List<ProductInfo> productInfos;
}
