package com.example.springSecurityPractice.controller;

import com.example.springSecurityPractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

}
