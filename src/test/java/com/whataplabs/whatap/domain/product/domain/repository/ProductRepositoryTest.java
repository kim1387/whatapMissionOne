package com.whataplabs.whatap.domain.product.domain.repository;

import com.whataplabs.whatap.domain.product.domain.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.whataplabs.whatap.domain.product.ProductFixtures.PRODUCT_ONE_ENTITY;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("product 저장 함수 test")
    void saveCityTest() {
        // given

        // when
        Product actualProduct = productRepository.save(PRODUCT_ONE_ENTITY);
        Product expectedProduct = PRODUCT_ONE_ENTITY;

        // then
        assertAll(
                () -> assertEquals(expectedProduct.getName(), actualProduct.getName()),
                () -> assertEquals(expectedProduct.getIntroContent(), actualProduct.getIntroContent()),
                () -> assertEquals(expectedProduct.getPrice(), actualProduct.getPrice())
        );
    }
}