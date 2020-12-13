package com.marvin.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.marvin.enumeration.ProjectEnvirment;

@ConfigurationProperties(prefix = "piracy")
public class PiracyExceptionProperties {//异常通知的配置

	@Value("$piracy.project-name:${spring.application.name:project}")//这里可以使用spring.application.name替代piracy.project-name,都可以给注入项目工程名称
	private String projectName;//工程名称
	
	private ProjectEnvirment env = ProjectEnvirment.DEVELOP;//项目的环境，默认是dev

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public ProjectEnvirment getEnv() {
		return env;
	}

	public void setEnv(ProjectEnvirment env) {
		this.env = env;
	}
	
}
