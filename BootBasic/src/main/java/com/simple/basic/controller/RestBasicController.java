package com.simple.basic.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	//데이터를 받는 방법
	//get방식 => 쿼리스트링 or 쿼리파라미터 이용해서 주소에 담아서 넘겨줌
	//post방식 => 폼형식 or JSON을 이용새서 body에 담아서 넘김
	
	//http://localhost:8181/getData?sno=1&first=홍길동
	@GetMapping("/getData")
	public SimpleVO getData(SimpleVO vo) {
		
		System.out.println(vo.toString());
		
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	//http://localhost:8181/getData2?sno=1&first=홍길동
	//@RequestParam 사용시 매개변수 sno, first는 필수값. 클라이언트가 꼭 보내줘야 함 or error
	@GetMapping("/getData2")
	public SimpleVO getData2(@RequestParam("sno") int sno,
							 @RequestParam("first") String first) {
		
		System.out.println(sno);
		System.out.println(first);
		
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	@GetMapping("/getData3/{sno}/{first}")
	public SimpleVO getData3(@PathVariable("sno") int sno,
							 @PathVariable("first") String first) {
		
		System.out.println(sno);
		System.out.println(first);
		
		
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	
	//////////////////////////////////////////////////////////////
	//post방식의 데이터 받기
	
	//보내는 입장에서 form형식의 데이터를 써줘야함
	@PostMapping("/getForm")
	public SimpleVO getForm(SimpleVO vo) {
		
		System.out.println(vo.toString());
		
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	//JSON
	//보내는 입장에서는 json형식의 데이터를 써줘야함
	//받는 입장에서는 매개변수 앞에 @RequestBody 작성
	//{"son": "1", "first":"홍길동", "last" :"이순신"}
	@PostMapping("/getJSON")
	public SimpleVO getJSON(@RequestBody SimpleVO vo) {
		System.out.println(vo.toString());
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	
	//{"son": 1, "first":"홍길동", "last" :"이순신"}
	@PostMapping("/getJSON2")
	public SimpleVO getJSON2(@RequestBody Map<String, Object> map ) {
		
		System.out.println(map.toString());
		
		return new SimpleVO(2, "이", "길동", LocalDateTime.now() );
	}
	
	////////////////////////////////////////////////////////////////////////
	//consumes = 반드시 이 타입으로 보내라! 명시
	//produces = 내가 이 타입으로 줄게! 명시 (default = json), xml을 사용하려면 xml데이터바인딩 라이브러리가 필요
	
	//보내는 타입은 text로 줄게, 너는 json데이터로 보내라
	@PostMapping(value ="/getResult", produces = "test/plain", consumes = "application/json")
	public String getResult(@RequestBody String str) {
		
		System.out.println(str);
		
		return "<h3>문자열</h3>";
	}
	
	//응답문서 직접 작성하기 = ResponseEntity<보낼데이터타입>
	@PostMapping("/createResponse")
	public ResponseEntity<SimpleVO> createResponse() {
		
		SimpleVO vo = new SimpleVO(1, "홍", "길동", LocalDateTime.now());
		//1st
		//ResponseEntity<SimpleVO> result = new ResponseEntity<>(vo, HttpStatus.OK); //데이터, 상태코드
		
		//2nd
		//헤더에 대한 내용정의
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "JSON WEB TOKEN~~");
		header.add("Content-Type", "application/json");
		header.add("Acess-Control-Allow-Origin", "*");
		
		ResponseEntity<SimpleVO> result = new ResponseEntity<SimpleVO>(vo, header, HttpStatus.OK); //데이터, 헤더, 상태코드
		
		return result;
	}
	
	//////////////////////////////////////////////////////////////////
	/*
	클라이언트 fetch
	요청주소 : /api/v1/getData
	메서드 : get
	클라이언트에서 보낼데이터 : num, name
	서버에서 보낼데이터 : SimpleVO
	받을 수 있는 restAPI를 생성 
	*/
	
	//선생님 풀이
	@GetMapping("/api/v1/getData/{num}/{name}")
	public ResponseEntity<SimpleVO> getFetch(@PathVariable("num") int num,
											 @PathVariable("name") String name) {
		
		System.out.println(num); //데이터베이스~~
		System.out.println(name); //데이터베이스~~
				
		return new ResponseEntity<>(new SimpleVO(4, "kim", "health", LocalDateTime.now()), HttpStatus.OK);
	}
	
	// 내 풀이
	@GetMapping("/api/v1/getData")
	public ResponseEntity<SimpleVO> getV1Data(String num, String name) {
		System.out.println(num);
		System.out.println(name);
		
		SimpleVO vo = new SimpleVO(3, "이", "순신", LocalDateTime.now());
		ResponseEntity<SimpleVO> result = new ResponseEntity<SimpleVO>(vo, HttpStatus.OK);
		
		return result;
	}
	
	
	
	
	
	/*
	클라이언트 fetch
	요청주소 : /api/v1/getInfo
	메서드 : post
	클라이언트에서 보낼데이터 : num, name
	서버에서 보낼데이터 : 리스트<SimpleVO>
	받을 수 있는 restAPI를 생성
	ResponseEntity로 응답
	*/
	
	//선생님 풀이
	@CrossOrigin("http://localhost:3000")
	@PostMapping(value="/api/v1/getInfo", consumes = "application/json")
	public ResponseEntity<List<SimpleVO>> getFetch2(@RequestBody Map<String, Object> map){
		
		System.out.println(map.toString());
		
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO(5, "배", "부르다", LocalDateTime.now()));
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//내풀이
	@PostMapping("/api/v1/getInfo")
	public ResponseEntity<List<SimpleVO>> getV1Info(@RequestBody Map<String, Object> map) {
		
		System.out.println(map.toString());
		
		List<SimpleVO> list = new ArrayList<>();
		list.add(new SimpleVO(3, "이", "순신", LocalDateTime.now()));
		
		ResponseEntity<List<SimpleVO>> result = new ResponseEntity<List<SimpleVO>>(list, HttpStatus.OK);
		
		return result;
	}
	
}
