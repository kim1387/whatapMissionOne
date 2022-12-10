package com.whataplabs.whatap.domain.product.domain.entity;

import com.whataplabs.whatap.global.domain.BaseEntity;
import java.util.Objects;
import javax.persistence.*;
import lombok.*;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
public class Product extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "intro_content", nullable = false)
  private String introContent;

  @Column(name = "price", nullable = false)
  private Integer price;

  @Builder
  public Product(String name, String introContent, Integer price) {
    this.name = name;
    this.introContent = introContent;
    this.price = price;
    this.isActivated = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id)
        && Objects.equals(name, product.name)
        && Objects.equals(introContent, product.introContent)
        && Objects.equals(price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, introContent, price);
  }
}
