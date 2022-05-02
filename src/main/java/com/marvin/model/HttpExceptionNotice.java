package com.marvin.model;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
public class HttpExceptionNotice extends CalmaNotice {
    protected String url;

    protected Map<String, String> paramInfo;

    protected String requestBody;

    protected Map<String, String> headers;

    protected String requestMethod;

    public HttpExceptionNotice(Throwable e, String projectName, String url,
                               Map<String, String> paramInfo, String requestBody, Map<String, String> headers,String requestMethod) {
        super(e, null, projectName);
        this.url = url;
        this.paramInfo = paramInfo;
        this.requestBody = requestBody;
        this.headers = headers;
        this.requestMethod = requestMethod;
    }

    @Override
    public String createText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("工程信息：").append(projectName).append("\r\n");
        stringBuilder.append("接口地址：").append(url).append("\r\n");
        if (paramInfo != null && paramInfo.size() > 0) {
            stringBuilder.append("接口参数：").append("\r\n")
                    .append(paramInfo.entrySet().stream()
                            .map(x -> String.format("%s::%s", x.getKey(), x.getValue())).collect(Collectors.joining("\r\r")))
                    .append("\r\n");
        }
        if (requestBody != null) {
            stringBuilder.append("请求体数据：").append(requestBody).append("\r\n");
        }
        if (headers != null && headers.size() > 0) {
            stringBuilder.append("请求头：").append("\r\n");
            stringBuilder.append(headers.entrySet().stream()
                    .map(x -> String.format("%s::%s", x.getKey(), x.getValue())).collect(Collectors.joining(",\t")));
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("请求方法：").append(requestMethod).append("\r\n");
        stringBuilder.append("类路径：").append(classPath).append("\r\n");
        stringBuilder.append("方法名：").append(methodName).append("\r\n");
        if (params != null && params.size() > 0 &&
                params.stream().anyMatch(Objects::nonNull)) {//这里是为了防止有参请求的参数为null
            stringBuilder.append("参数信息：")
                    .append(params.stream().map(Object::toString).collect(Collectors.joining("\t,\t")))
                    .append("\r\n");
        }
        stringBuilder.append("异常信息：").append(String.join("\r\n caused by: ", exceptionMessage)).append("\r\n");
        stringBuilder.append("异常追踪：").append("\r\n").append(String.join("\r\n", traceInfos)).append("\r\n");
        stringBuilder.append("最后一次出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\r\n");
        return stringBuilder.toString();
    }

}
