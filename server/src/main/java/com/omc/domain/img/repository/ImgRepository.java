package com.omc.domain.img.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omc.domain.img.entity.Img;

public interface ImgRepository extends JpaRepository<Img, Long> {

	void deleteByProductId(Long productId);

	List<Img> findAllByProductId(Long productId);
}
