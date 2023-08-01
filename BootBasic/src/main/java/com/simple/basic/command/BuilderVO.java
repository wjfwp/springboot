package com.simple.basic.command;

public class BuilderVO {

	//빌더패턴을 사용할 수 있도록 설계
	//1. 멤버변수
	private String name;
	private int age;

	//8.생성자 생성
	private BuilderVO(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
	}
	
	
	//6. 외부에서 객체를 요구할 때, 내부에 있는 Builder클래스를 반환
	public static Builder builder() {
		return new Builder();
	}
	
	//toString
	@Override
	public String toString() {
		return "BuilderVO [name=" + name + ", age=" + age + "]";
	}


	//2. 내부클래스
	public static class Builder {
		//3. 내부클래스 멤버변수를 생성
		private String name;
		private int age;
		
		//4. 내부클래스 생성자를 제한
		private Builder() {
			
		}
		
		//5.세터메서드 생성 - 나 자신을 다시 반환
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.age = age;
			return this;
		}
		
		//7.build메서드를 실행하면 멤버변수를 외부클래스에 저장
		public BuilderVO build() {
			BuilderVO vo = new BuilderVO(this);
			return vo;
		}
				
	}





}
