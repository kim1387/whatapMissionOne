package com.whataplabs.whatap.domain.product.domain.repository;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("select p from Product p")
  Page<Product> findProductsWithPagination(Pageable pageable);
}
