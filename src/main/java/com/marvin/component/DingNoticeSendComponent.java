package com.marvin.component;

import com.marvin.client.Client;
import com.marvin.model.CalmaNotice;
import com.marvin.model.DingContent;
import com.marvin.model.DingMarkdown;
import com.marvin.model.DingdingNotice;
import com.marvin.util.CalmaValueResolver;
import com.marvin.util.DingDingProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Describe: 发送钉钉通知的组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Component
@Slf4j
public class DingNoticeSendComponent<T extends CalmaNotice> implements NoticeSendComponent<T> {

    @Autowired
    private DingDingProperty dingDingProperty;

    //todo:
    private final CalmaValueResolver<T> resolver;

    private final Client client;

    public DingNoticeSendComponent(CalmaValueResolver<T> resolver, Client client) {
        this.client = client;
        this.resolver = resolver;
    }

    //将异常结构体组装好
    @Override
    public void send(T exceptionNotice) {
        try {
            log.info("--------------->>>>>send<<<<<<<<-----------------------");
            String text = resolver.resolve(exceptionNotice);
            DingdingNotice dingdingNotice = null;
            if (isMarkDown()){
                DingMarkdown markdown = new DingMarkdown();
                markdown.setText(text);
                dingdingNotice = dingDingProperty.createDingdingMarkdownNotice(markdown);
                dingdingNotice.setMarkdown(markdown);
            }else {
                DingContent content = new DingContent();
                content.setContent(text);
                dingdingNotice = dingDingProperty.createDingdingTextNotice(content);
                dingdingNotice.setText(content);
            }
            client.doSend(dingdingNotice);
        } catch (Exception e) {
            log.info(e.getCause().toString() + "\n \n");
            e.printStackTrace();
        }
    }

    private boolean isMarkDown() {
        if (!StringUtils.isEmpty(dingDingProperty.getMsgtype()) && dingDingProperty.getMsgtype().equalsIgnoreCase("markdown")){
            return true;
        }
        return false;
    }
}
