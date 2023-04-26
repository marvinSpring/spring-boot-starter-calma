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
public class HttpNotice extends CalmaNotice {
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
        if (requestBody != null) {
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
        if (requestBody != null) {
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
        stringBuilder.append("###### 最后一次出现时间：").append(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\r\n");
        return stringBuilder.toString();
    }

    /*public static void main(String[] args) {
        try {
            test1();
        }catch (Exception e){
            Object [] args1 = new Object[]{"Abc","321",32};
            Map<String,String> paramInfos = new HashMap<>();
            paramInfos.put("userId","321312");
            paramInfos.put("age","13");

            Map<String,String> header = new HashMap<>();
            header.put("cookie","dfsafujaseiofj4156as4f4ds5465df4asf65ijoi");
            header.put("Authentication","bear dj2ijdfjewi");

            CalmaNotice example = new HttpExceptionNotice(e,"calma-boot","http://www.baidu.com/query",paramInfos,"{\"modelId\":\"636067641399971840\",\"targetInfos\":[{\"apiName\":\"管网经营管理平台-管理组织机构\",\"id\":\"808559858591862784\",\"apiCode\":\"MDM-HNIC-GLZZJG-CS\",\"disDataList\":null,\"disTask\":null,\"expCode\":\"\",\"expName\":\"\",\"filterSql\":\"\",\"isExp\":\"0\",\"operType\":null,\"setTask\":\"1\",\"targetId\":\"808500860081541120\",\"targetName\":\"管网经营管理平台\"}],\"ids\":\"" + "ids" + "\"}",header,"query");
            example.createTime = LocalDateTime.now();
            example.params = Arrays.asList("11","33","dsa");
//		System.out.println(example.createText());
            System.out.println(example.createMarkdown());
        }

    }

    public static void test1(){
        test2();
    }

    public static void test2(){
        throw new ArrayIndexOutOfBoundsException("数组下标越界");

    }*/

}
