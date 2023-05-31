package com.marvin.context.support.component;

import com.marvin.context.support.client.Client;
import com.marvin.factory.DingMessageFactory;
import com.marvin.model.notice.CommonNotice;
import com.marvin.resolver.CalmaValueResolver;
import com.marvin.model.message.ding.DingDingProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Describe: 发送钉钉通知的组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Component
@Slf4j
public class DingNoticeSendComponent<T extends CommonNotice> implements NoticeSendComponent<T> {

    @Autowired
    private DingDingProperty dingDingProperty;

    private final CalmaValueResolver<T> resolver;

    private final Client client;

    @SuppressWarnings("all")
    public DingNoticeSendComponent(CalmaValueResolver<T> resolver, Client client) {
        this.client = client;
        this.resolver = resolver;
    }

    //将异常结构体组装好
    @Override
    public void send(T exceptionNotice) {
        try {
            if (log.isDebugEnabled()) {
                log.info("--------------->>>>>ding<<<<<<<<-----------------------");
            }
            String text = resolver.resolve(exceptionNotice);
            client.doSend(DingMessageFactory.createDingMessage(dingDingProperty,text));
        } catch (Exception e) {
            log.info(e.getCause().toString() + "\n \n");
            e.printStackTrace();
        }
    }


}
