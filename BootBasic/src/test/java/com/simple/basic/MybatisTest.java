package com.simple.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {

	@Autowired
	TestMapper testMapper;
	
	@Test
	public void testCode() {
		System.out.println(testMapper.getTime());
	}
}
