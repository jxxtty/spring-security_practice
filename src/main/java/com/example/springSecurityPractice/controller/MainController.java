package com.example.springSecurityPractice.controller;

import com.example.springSecurityPractice.domain.Member;
import com.example.springSecurityPractice.domain.dto.MemberDto;
import com.example.springSecurityPractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto){
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberDto memberDto, HttpServletRequest request, Model model){
        // 연습프로젝트의 간결성을 위해 아이디 중복체크를 하지 않음(새로 생성되는 아이디는 중복이 없다고 가정한다)
        Member saveMember = memberService.join(memberDto);
        model.addAttribute("joinMsg", "회원가입이 완료되었습니다 + " + saveMember.getMember_name());
        model.addAttribute("joinMsg2", "로그인해주세요.");
        return "main";
    }

    @GetMapping("/login/result")
    public String loginSuccess(){
        return "member/main";
    }

}
