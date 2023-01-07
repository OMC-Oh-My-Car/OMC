package com.omc.domain.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.member.entity.Member;
import com.omc.domain.product.entity.LikeHistory;
import com.omc.domain.product.entity.Product;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, Long> {

	Optional<LikeHistory> findByMemberIdAndProductId(Long memberId, Long productId);
}
