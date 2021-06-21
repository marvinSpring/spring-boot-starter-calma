package com.marvin.testModel;

import com.marvin.anno.CalmaExceptionListener;
import org.springframework.stereotype.Component;


@CalmaExceptionListener
@Component
public class TestCalma {
	
	public void test11(String name) {
		System.out.println("name:"+name);
		new IException();
	}
	
	class IException{
		public IException() {
			get();
		}
		public void get() {
			int i = 5/0;
		}
	}
}
