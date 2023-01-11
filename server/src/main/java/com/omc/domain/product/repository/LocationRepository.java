package com.omc.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

	void deleteByProductId(Long productId);

	List<Location> findAllByProductId(Long productId);
}
