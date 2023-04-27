package com.marvin.model.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Describe: 异常信息
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonNotice extends Notice{//异常的结构体

	String title;//异常类名称

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime createTime = LocalDateTime.now();//通知时间
	
	String uid;//异常标识码
	
	String methodName;//方法名称
	
	String classPath;//类路径
	
	List<Object> params;//出现异常的方法参数
	
	List<String> exceptionMessage;//出现异常的异常原因信息
	
	List<String> traceInfos;//异常的追踪栈

	String projectName;//工程名称
	
	public CommonNotice(Throwable e, Object [] args, String projectName) {
		this.exceptionMessage = giveMeExceptionMessage(e);
		this.params = args==null?null:Arrays.stream(args).collect(Collectors.toList());
		List<StackTraceElement> list = stackTrace(e);
		if(list.size()>0) {
			this.traceInfos=list.stream().map(StackTraceElement::toString).collect(Collectors.toList());
			this.methodName = list.get(0).getMethodName();
			this.classPath =list.get(0).getClassName();
		}
		this.projectName=projectName!=null&&projectName.length()>0?projectName:"工程名称未正确获取";
		this.uid = generateUid();
	}

	private String generateUid() {//生成异常的唯一标识码
		return DigestUtils.md5DigestAsHex(String.format("%s-%s",exceptionMessage,traceInfos.size()>0?traceInfos.get(0):"").getBytes());
	}

	private List<StackTraceElement> stackTrace(Throwable e) {//追踪异常栈信息
		ArrayList<StackTraceElement> list = new ArrayList<StackTraceElement>();
		addStackTrace(list,e);
		Throwable cause = e.getCause();
		while(cause!=null) {
			addStackTrace(list,cause);
			cause = cause.getCause();
		}
		return list;
	}

	private void addStackTrace(ArrayList<StackTraceElement> list, Throwable e) {//添加异常栈，过滤掉cglib动态生成的文件——<generated>
		list.addAll(0,Arrays.stream(e.getStackTrace()).filter(x->!x.getFileName().equals("<generated>")).collect(Collectors.toList()));
	}

	private List<String> giveMeExceptionMessage(Throwable exception) {//获取异常原因信息
		ArrayList<String> list = new ArrayList<String>();
		giveMeExceptionMessage(exception,list);
		return list;
	}

	private void giveMeExceptionMessage(Throwable exception, ArrayList<String> list) {//拼接异常字符串  异常 异常信息
		list.add(String.format("%s:%s",exception.getClass(),exception.getMessage()));
		Throwable cause = exception.getCause();
		if(cause!=null) {
			giveMeExceptionMessage(cause,list);
		}
	}
	
	public String createText() {//将异常格式化返回——梦的美容院
		StringBuilder builder = new StringBuilder();
		builder.append("工程名称：").append(projectName).append("\r\n");
		builder.append("类路径：").append(classPath).append("\r\n");
		builder.append("方法名称：").append(methodName).append("\r\n");
		if(params!=null&&!CollectionUtils.isEmpty(params)&&//如果有参数追加参数到内容中
				params.stream().anyMatch(Objects::nonNull)) {//这里是为了防止有参的参数为null
			builder.append("参数信息：").append(String.join(",",params.stream().map(Object::toString).collect(Collectors.toList()))).append("\r\n");
		}
		builder.append("异常信息：").append(String.join("cause by : \r\n", exceptionMessage)).append("\r\n");
		builder.append("异常追踪：").append(String.join("\r\n",traceInfos)).append("\r\n");
		builder.append("出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\r\n");
		return builder.toString();
	}

	public String createMarkdown(){
		StringBuilder builder = new StringBuilder();
		builder.append("#### 工程名称：").append(projectName).append("\r\n");
		builder.append("##### 类路径：`").append(classPath).append("`\r\n");
		builder.append("##### 方法名称：").append(methodName).append("\r\n");
		if(params!=null&&!CollectionUtils.isEmpty(params)&&//如果有参数追加参数到内容中
				params.stream().anyMatch(Objects::nonNull)) {//这里是为了防止有参的参数为null
			builder.append("##### 参数信息：").append(params.stream().map(Object::toString).collect(Collectors.joining(","))).append("\r\n");
		}
		builder.append("##### 异常信息：\r\n`").append(String.join("cause by : \r\n", exceptionMessage)).append("`\r\n");
		builder.append("##### 异常追踪：\r\n`").append(String.join("`\r\n`",traceInfos)).append("`\r\n");
		builder.append("###### 出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\r\n");
		return builder.toString();
	}

}
