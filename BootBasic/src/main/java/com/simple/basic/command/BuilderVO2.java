package com.simple.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter, setter, toString
@AllArgsConstructor //모든생성자
@NoArgsConstructor //기본생성자
@Builder
public class BuilderVO2 {
	
	private String name;
	private int age;
}
