package com.omc.domain.img.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.img.entity.ProductImg;

public interface ImgRepository extends JpaRepository<ProductImg, Long> {

	void deleteByProductId(Long productId);

	List<ProductImg> findAllByProductId(Long productId);
	ProductImg findFirstByProductId(Long productId);
}
