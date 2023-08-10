package com.simple.basic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SessionController {

	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		
		//인증이 없는 경우
//		if(session.getAttribute("username") == null) {
//			return "redirect:/user/login";
//		}
		
		System.out.println("컨트롤러 실행됨");
		System.out.println("=========>");
		
		
		return "user/mypage";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/loginForm")
	public String loginForm(/*HttpServletRequest request*/
							HttpSession session) {
		
		//HttpSession session = request.getSession();
		//로그인 시도(성공)
		if(true) {
			session.setAttribute("username", "aaa123");
			return "redirect:/user/mypage";
			
		} else {
			return "redirect:/"; //홈
		}
		
		
	}
	
	@GetMapping("/modify")
	public String modify(HttpSession session) {
		

		//인증이 없는 경우
//		if(session.getAttribute("username") == null) {
//			return "redirect:/user/login";
//		}
		
		return "user/modify";
	}
	
}
