package com.coding404.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coding404.demo.command.UserVO;

@Service //서비스 빈으로 선언 반드시
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	
	//loginProcessURL이 호출될 때 loadUserByUsername함수를 자동으로 연결.
	//화면에서는 기본적으로 username이라는 파라미터로 지정해야 한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("============loadUserByuserName==============");
		
		System.out.println(username);
		//로그인처리 - password는 시큐리티가 알아서 처리해준다.
		UserVO vo = userMapper.login(username);
		
		System.out.println(vo);
		
		//vo가 null이 아니라는 것은 회원정보가 있다라는 의미.
		if(vo != null) { //회원정보가 있다면.
			//스프링 시큐리티가 이 값을 받아가서 password를 확인한 후에, 정상유저라고 판별이 되면
			//시큐리티세션(new Authentication(new MyUserDetails))) 형태로 저장을 시킨다.
			return new MyUserDetails(vo);
			//시큐리티 설정파일에 defaultSuccessURL을 작성 //defaultSuccessURL 로그인 성공시에 이동할 경로 설정
		}
		
		return null; //회원정보가 없다면 null을 반환받고 끝
	}

}
