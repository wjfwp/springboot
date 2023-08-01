package com.simple.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;

@Configuration //이거 설정파일이야
public class WebConfig implements WebMvcConfigurer { //자바 빈 설정을 하기 위해서 상속
	
//	//IOC확인
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	//value어노테이션의 사용 - application.properties파일의 값을 참조하는데 사용
//	@Value("${spring.datasource.url}")
//	String url;
//
//	@Bean // 이 메서드를 빈으로 생성
//	public void test() {
//		TestBean test = applicationContext.getBean(TestBean.class);
//		System.out.println("TestBean이 빈으로 등록됨:" + test);
//		
//		HomeController con = applicationContext.getBean(HomeController.class);
//		System.out.println("homeController가 빈으로 등록됨:" + con);
//		
//		int count = applicationContext.getBeanDefinitionCount();
//		System.out.println("IOC안의 빈의 개수" + count);
//		
//		System.out.println("applicationproperties 키 값:" + url);
//		
//	}
	
	
	
	
	//빈생성의 2가지 전략 -
	// @Controller, @Service 등을 이용해서 빈으로 등록
	// 스프링설정파일에 빈으로 등록
	// return 객체를 반환하는 모형을 만들면, 빈으로 등록
	@Bean
	public TestBean test2() {
		TestBean b = new TestBean();
		return b;
	}
}
