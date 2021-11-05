package com.example.springSecurityPractice.domain.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberDto {
    private String loginId;
    private String password;
    private String member_name;
    private String phone_number;
    private String email;
}
