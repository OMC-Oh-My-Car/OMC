package com.omc.domain.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.product.entity.StopHistory;

public interface StopHistoryRepository extends JpaRepository<StopHistory, Long> {

	// findTopByProductIdOrderByStopDateDesc
	StopHistory findTopByProductIdOrderByCreatedAtDesc(Long productId);
}
