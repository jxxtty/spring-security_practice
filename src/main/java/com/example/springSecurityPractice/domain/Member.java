package com.example.springSecurityPractice.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String member_name;

    private String phone_number;

    private String email;


    @Builder
    public Member(String loginId, String password, String member_name, String phone_number, String email) {
        this.loginId = loginId;
        this.password = password;
        this.member_name = member_name;
        this.phone_number = phone_number;
        this.email = email;
    }
}
