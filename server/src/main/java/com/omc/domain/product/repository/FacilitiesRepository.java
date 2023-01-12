package com.omc.domain.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.Facilities;

public interface FacilitiesRepository extends JpaRepository<Facilities, Long> {

	void deleteByProductId(Long productId);

	List<Facilities> findAllByProductId(Long productId);

	List<Facilities> findAllByKeywordContaining(String keyword);


}
