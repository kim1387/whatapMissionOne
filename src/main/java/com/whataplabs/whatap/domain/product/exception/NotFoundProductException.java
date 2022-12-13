package com.whataplabs.whatap.domain.product.exception;

import com.whataplabs.whatap.global.exception.BusinessException;
import com.whataplabs.whatap.global.response.ErrorCode;

public class NotFoundProductException extends BusinessException {

  public NotFoundProductException() {
    super(ErrorCode.NOT_FOUND_PRODUCT_ENTITY);
  }
}
