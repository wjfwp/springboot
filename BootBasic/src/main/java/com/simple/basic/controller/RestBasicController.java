package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.basic.command.SimpleVO;

@RestController //@ResponseBody + @Controller (HomeController 확인해보기)
public class RestBasicController {

	@GetMapping("/basic")
	public String basic() {
		return "<h3>hello world</h3>";
	}
	
	//데이터를 보내는 방법 -> @ResponseBody = 자바의 객체를 JSON형식으로 자동 변환
	@GetMapping("/getObject")
	public SimpleVO getObject() {
		
		SimpleVO vo = new SimpleVO(1, "홍", "길동", LocalDateTime.now());
		return vo;
	}
	
	@GetMapping("/getMap")
	public Map<String, Object> getMap() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("name", "홍길동");
		
		return map;
	}
	
	@GetMapping("/getList")
	public List<SimpleVO> getList() {
		
		List<SimpleVO> list = new ArrayList<>();
		list.add( new SimpleVO(1, "홍", "길동", LocalDateTime.now()) );
		list.add( new SimpleVO(2, "이", "길동", LocalDateTime.now()) );
		
		return list;
	}
	
	
	
	
}
