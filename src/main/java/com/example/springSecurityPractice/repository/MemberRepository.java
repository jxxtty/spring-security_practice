package com.example.springSecurityPractice.repository;

import com.example.springSecurityPractice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
