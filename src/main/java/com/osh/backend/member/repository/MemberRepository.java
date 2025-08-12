package com.osh.backend.member.repository;

import com.osh.backend.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberEmail(String memberEmail);
    
}
