package com.omc.domain.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAllByMemberId(Long id, Pageable pageable);

	Page<Product> findBySubjectContainingOrDescriptionContainingAndIdIn(String subject,
																		String description,
																		List<Long> id,
																		Pageable pageable);

	Page<Product> findBySubjectContainingOrDescriptionContaining(String searchQuery,
																 String description,
																 Pageable pageable);

	Page<Product> findByIdIn(List<Long> productIds, Pageable pageable);
}
