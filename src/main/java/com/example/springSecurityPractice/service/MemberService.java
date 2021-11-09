package com.example.springSecurityPractice.service;

import com.example.springSecurityPractice.domain.Member;
import com.example.springSecurityPractice.domain.Role;
import com.example.springSecurityPractice.domain.dto.MemberDto;
import com.example.springSecurityPractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member join(MemberDto memberDto) {
        // 패스워드 암호화
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


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> findMemWrapper = memberRepository.findByLoginId(loginId);
        Member findMem = findMemWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (findMem.getEmail().contains("@pincar.co.kr")) { // 이메일로 ADMIN구분
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(findMem.getLoginId(), findMem.getPassword(), authorities);
    }
}
