package com.coding404.demo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coding404.demo.command.UserVO;

//이 객체는 화면에 전달이 되는데, 화면에서 여러분이 사용할 값들은 getter로 생성
public class MyUserDetails implements UserDetails {

	//멤버변수로 UserVO객체를 받는다.
	private UserVO userVO;
	
	public MyUserDetails(UserVO vo) { //vo를 받는 생성자
		this.userVO = vo;
	}
	
	//화면에서 권한도 사용할 수 있게 해주고 싶다면? getter 만들면 된다.
	//그 이외에 만들고 싶은거 있으면 getter로 다 만들어주면 됨
	public String getRole() {
		return userVO.getRole();
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		//UserVO가 가지고 있는 권한을 리스트에 담아서 반환시키면, 스프링 시큐리티가 참조해서 사용한다.
		List<GrantedAuthority> list = new ArrayList<>();
		
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userVO.getRole();
			}
		});
		
		return list;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //계정이 만료되지 않았습니까? true = 네
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; //계정이 락이 걸리지 않았습니까? true = 네
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; //비밀번호가 만료되지 않았습니까? true = 네
	}

	@Override
	public boolean isEnabled() {
		return true; //사용할 수 있는 계정입니까? true = 네
	}

}
