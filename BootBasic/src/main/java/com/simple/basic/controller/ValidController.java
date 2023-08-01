package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemberVO;
import com.simple.basic.command.ValidVO;

@Controller
@RequestMapping("/valid")
public class ValidController {

	//화면
	@GetMapping("/view")
	public String view() {
		return "valid/view";
	}
	
	//폼요청
	@PostMapping("/viewForm")
	public String viewForm(@Valid @ModelAttribute("vo") ValidVO vo, Errors errors, Model model) {
		
		
		if(errors.hasErrors()) { //에러가 있다면 true, 없다면 false
			//1. 유효성 검사에 실패한 에러 확인
			List<FieldError> list = errors.getFieldErrors();
			
			//2. 반복처리
			for(FieldError err : list  ) {
				//System.out.println(err);
				//System.out.println(err.getField()); //에러가 난 필드명
				//System.out.println(err.getDefaultMessage()); //메시지 출력
				//System.out.println(err.isBindingFailure()); //유효성 검사에 의해서 err라면 false, 아니라면 true
				
				if(err.isBindingFailure()) { //유효성 검사 에러면 false, 에초에 자바내부 에러면 true
					model.addAttribute("valid_" + err.getField(), "잘못된 값 입력입니다. 숫자만 입력하세요");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			return "valid/view"; //실패시 원래 화면으로
		}//err end
		
		System.out.println(vo.toString());
		System.out.println(errors);
		
		return "valid/result";
	}
	
	
	/////////////////////////////////////////////////////////////////////////
	////quiz01
	
	//화면
	@GetMapping("/quiz01")
	public String quiz01() {
		return "valid/quiz01";
	}
	
	//폼요청
	@PostMapping("/quizForm")
	public String quizForm(@Valid @ModelAttribute("vo") MemberVO vo, Errors errors, Model model ) {
		
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
				if(err.isBindingFailure()) { //유효성 검사 에러면 false, 에초에 자바내부 에러면 true
					model.addAttribute("valid_" + err.getField(), "잘못된 값 입력");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
			}
			
			return "valid/quiz01";
		}
		
		return "valid/quiz01_result";
	}
	

	
}
