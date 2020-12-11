package com.marvin.testModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marvin.anno.PiracyExceptionListener;

@PiracyExceptionListener
@Component
public class TestPiracy {
	
	public void test(String name) {
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
