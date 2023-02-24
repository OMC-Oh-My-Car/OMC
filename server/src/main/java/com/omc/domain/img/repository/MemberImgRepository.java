package com.omc.domain.img.repository;

import com.omc.domain.img.entity.Img;
import com.omc.domain.img.entity.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {

    void deleteByMemberId(Long id);
}
