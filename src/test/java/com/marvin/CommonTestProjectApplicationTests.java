package com.marvin;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.marvin.testModel.TestPiracy;

@SpringBootTest
@EnableAutoConfiguration
class CommonTestProjectApplicationTests {

	@Autowired
	TestPiracy test;

	@Test
	public void test23() {
		test.test("参数");
	}

}
