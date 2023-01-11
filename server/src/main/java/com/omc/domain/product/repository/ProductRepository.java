package com.omc.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAllByMemberId(Long id, Pageable pageable);

}
