package com.simple.basic.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoVO {
	
	private int mno;
	
	@NotBlank(message="내용은 필수 입니다.")
	private String memo;
	
	@Pattern(regexp="^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$", message="전화번호 형식에 맞춰서 입력해주세요")
	private String phone;
	
	@Pattern(regexp="[0-9]{4}", message="비밀번호는 숫자4자리 입니다")
	private String pw;
	
	private String secret;
}
