package com.simple.basic;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
	
	String getTime();
	

}
