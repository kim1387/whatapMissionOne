package com.whataplabs.whatap.domain.product.domain.repository;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
