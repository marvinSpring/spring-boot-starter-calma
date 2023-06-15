package com.marvin.model.notice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
public class HttpNotice extends CommonNotice {
    protected String url;

    protected Map<String, String> paramInfo;

    protected String requestBody;

    protected Map<String, String> headers;

    protected String requestMethod;

    public HttpNotice(Throwable e, String projectName, String url,
                      Map<String, String> paramInfo, String requestBody, Map<String, String> headers, String requestMethod) {
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
                    .append(String.join("\r\r", paramInfo.entrySet().stream()
                            .map(x -> String.format("%s::%s", x.getKey(), x.getValue())).collect(toList())))
                    .append("\r\n");
        }
        if (!StringUtils.isEmpty(requestBody)) {
            stringBuilder.append("请求体数据：").append(requestBody).append("\r\n");
        }
        if (headers != null && headers.size() > 0) {
            stringBuilder.append("请求头：").append("\r\n");
            stringBuilder.append(String.join(",\t", headers.entrySet().stream()
                    .map(x -> String.format("%s::%s", x.getKey(), x.getValue())).collect(toList())));
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("请求方法：").append(requestMethod).append("\r\n");
        stringBuilder.append("类路径：").append(classPath).append("\r\n");
        stringBuilder.append("方法名：").append(methodName).append("\r\n");
        if (params != null && params.size() > 0 &&
                params.stream().anyMatch(Objects::nonNull)) {//这里是为了防止有参请求的参数为null
            stringBuilder.append("参数信息：")
                    .append(String.join("\t,\t", params.stream().map(x -> x.toString()).collect(toList())))
                    .append("\r\n");
        }
        stringBuilder.append("异常信息：").append(String.join("\r\n caused by: ", exceptionMessage)).append("\r\n");
        stringBuilder.append("异常追踪：").append("\r\n").append(String.join("\r\n", traceInfos)).append("\r\n");
        stringBuilder.append("首次出现时间：").append(exceptionStatisticDto.getFirstAppearTime()).append("\r\n");
        stringBuilder.append("出现次数：").append(exceptionStatisticDto.getCount()).append("\r\n");
        stringBuilder.append("出现频率：").append(exceptionStatisticDto.getFrequency()).append("%").append("\r\n");
        stringBuilder.append("最后一次出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\r\n");
        return stringBuilder.toString();
    }

    public String createMarkdown() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("#### 工程信息：").append(projectName).append("\r\n");
        stringBuilder.append("##### 接口地址：").append(url).append("\r\n");
        if (paramInfo != null && paramInfo.size() > 0) {
            stringBuilder.append("##### 接口参数：").append("\r\n")
                    .append(paramInfo.entrySet().stream()
                            .map(x -> String.format("` %s : %s `", x.getKey(), x.getValue())).collect(Collectors.joining("\r\r")))
                    .append("\r\n");
        }
        if (!StringUtils.isEmpty(requestBody)) {
            stringBuilder.append("##### 请求体数据：").append("\r\n`").append(requestBody).append("`\r\n");
        }
        if (headers != null && headers.size() > 0) {
            stringBuilder.append("##### 请求头：").append("\r\n");
            stringBuilder.append(String.join("\r\n", headers.entrySet().stream()
                    .map(x -> String.format("` %s : %s `", x.getKey(), x.getValue())).collect(toList())));
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("##### 请求方法：").append(requestMethod).append("\r\n");
        stringBuilder.append("##### 类路径：`").append(classPath).append("`\r\n");
        stringBuilder.append("##### 方法名：").append(methodName).append("\r\n");
        if (params != null && params.size() > 0 &&
                params.stream().anyMatch(Objects::nonNull)) {//这里是为了防止有参请求的参数为null
            stringBuilder.append("##### 参数信息：")
                    .append(String.join("\t,\t", params.stream().map(Object::toString).collect(toList())))
                    .append("\r\n");
        }
        stringBuilder.append("##### 异常信息：`").append(String.join("\r\n caused by: ", exceptionMessage)).append("`\r\n");
        stringBuilder.append("##### 异常追踪：").append("\r\n`").append(String.join("`\r\n`", traceInfos)).append("`\r\n");
        stringBuilder.append("###### 首次出现时间：").append(exceptionStatisticDto.getFirstAppearTime()).append("\r\n");
        stringBuilder.append("###### 出现次数：").append(exceptionStatisticDto.getCount()).append("\r\n");
        stringBuilder.append("###### 出现频率：").append(exceptionStatisticDto.getFrequency()).append("%").append("\r\n");
        stringBuilder.append("###### 最后一次出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\r\n");
        return stringBuilder.toString();
    }

}
