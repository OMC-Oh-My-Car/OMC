package com.omc.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
