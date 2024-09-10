package com.example.blog_crud.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.blog_crud.dto.MemberDTO;
import com.example.blog_crud.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입 
    private final MemberService memberService;

    @GetMapping("/member/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping("/member/signup")
    public String postSignUpPage(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.postSignUpPage(memberDTO);
        return "login";
    }
    
    @GetMapping("/member/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/member/login")
    public String postLoginPage(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("login");
        MemberDTO loginResult = memberService.postLogin(memberDTO);
        if (loginResult != null) {
            // login 성공 
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }
}
