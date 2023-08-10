package com.simple.basic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.controller.HomeController;
import com.simple.basic.interceptor.UserAuthHandler;

@Configuration //이거 설정파일이야
public class WebConfig implements WebMvcConfigurer { //자바 빈설정을 하기위해서 상속

	
	//인터셉터로 사용할 클래스를 bean
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	//스프링설정에 인터셉터를 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		 registry.addInterceptor( userAuthHandler() )
		 		 .addPathPatterns("/user/**") //user로 시작하는 모든 요청검사
		 		 .excludePathPatterns("/user/login") //user/login제외
		 		 .excludePathPatterns("/user/loginForm"); //로그인메서드 제외
		 		 //.addPathPatterns("/memo/**")
		 		 //.addPathPatterns("/product/**")
		 		 //.addPathPatterns("/user/mypage")
		 		 //.addPathPatterns("/user/modify")
		 		 
		 //인터셉터는 여러개가 있을 수도 있는데, 추가하면 됩니다.
		 
		 		 
		
	}
	
	
	
	
	
	
	
	
	
//	//IOC확인
//	@Autowired
//	ApplicationContext applicationContext;
//	
//	//value어노테이션의 사용 - application.properties파일의 값을 참조하는데 사용
//	@Value("${spring.datasource.url}")
//	String url;
//		
//	@Bean //요 메서드를 빈으로 생성
//	public void test() {
//	
//		TestBean test = applicationContext.getBean(TestBean.class);
//		System.out.println("TestBean이 빈으로 등록됨:" + test);
//		
//		HomeController con = applicationContext.getBean(HomeController.class);
//		System.out.println("homeControlller가 빈으로 등록됨:" + con);
//		
//		int count = applicationContext.getBeanDefinitionCount();
//		System.out.println("IOC안에 빈의개수:" + count);
//		
//		
//		System.out.println("application프로퍼티 키값:" +url);
//		
//		
//	}
			
	//빈생성의 2가지 전략 - 
	//@Controller, @Service 등등 이용해서 빈으로 등록
	//스프링설정파일에 빈으로 등록
	//return 객체를 반환하는 모형을 만들면, 빈으로 등록
//	@Bean
//	public TestBean test2() {
//		TestBean b = new TestBean();
//		return b;
//	}
	
	
}
