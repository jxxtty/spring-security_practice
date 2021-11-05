package com.example.springSecurityPractice.service;

import com.example.springSecurityPractice.domain.Member;
import com.example.springSecurityPractice.domain.dto.MemberDto;
import com.example.springSecurityPractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member newMember = Member.builder()
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .member_name(memberDto.getMember_name())
                .phone_number(memberDto.getPhone_number())
                .email(memberDto.getEmail()).build();
        return memberRepository.save(newMember);
    }
}
