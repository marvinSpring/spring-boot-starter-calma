package com.marvin.testModel;

import org.springframework.stereotype.Component;

import com.marvin.anno.PiracyExceptionListener;

@PiracyExceptionListener
@Component
public class TestPiracy {
	
	public void test11(String name) {
		System.out.println("name:"+name);
//		new IException();
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
