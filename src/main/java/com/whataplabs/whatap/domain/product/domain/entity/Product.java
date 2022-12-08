package com.whataplabs.whatap.domain.product.domain.entity;

import com.whataplabs.whatap.global.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

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
  public Product(String name, String introContent) {
    this.name = name;
    this.introContent = introContent;
    this.isActivated = true;
  }
}
