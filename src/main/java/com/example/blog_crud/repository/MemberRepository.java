package com.example.blog_crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_crud.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // Email로 회원 정보 조회
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
