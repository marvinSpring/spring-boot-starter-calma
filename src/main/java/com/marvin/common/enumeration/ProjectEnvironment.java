package com.marvin.common.enumeration;

//项目环境
public enum ProjectEnvironment {

	DEVELOP("develop"),PREVIEW("preview"),ROLLBACK("rollback"),RELEASE("release"),TEST("test");
	
	private String name;
	
	ProjectEnvironment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
