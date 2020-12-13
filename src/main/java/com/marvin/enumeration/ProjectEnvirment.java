package com.marvin.enumeration;

//项目环境
public enum ProjectEnvirment {

	DEVELOP("develop"),PREVIEW("preview"),ROLLBACK("rollback"),RELEASE("release"),TEST("test");
	
	private String name;
	
	ProjectEnvirment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
