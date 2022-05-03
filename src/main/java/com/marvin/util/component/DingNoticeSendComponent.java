package com.marvin.util.component;

import com.marvin.model.loader.SmartExceptionLoader;
import com.marvin.model.send.DingContent;
import com.marvin.model.send.DingdingExceptionSendContext;
import com.marvin.util.CalmaNoticeTextResolver;
import com.marvin.util.DingDingProperty;
import com.marvin.util.client.Client;
import lombok.Data;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Describe: 发送钉钉通知的组件
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Data
public class DingNoticeSendComponent<T extends SmartExceptionLoader> implements NoticeSendComponent<T> {

    protected final org.apache.commons.logging.Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private DingDingProperty dingDingProperty;

    DingContent content;

    private final CalmaNoticeTextResolver<T> resolver;

    private final Client client;

    public DingNoticeSendComponent(CalmaNoticeTextResolver<T> resolver, Client client) {
        this.client = client;
        this.resolver = resolver;
    }

    //将异常结构体组装好
    @Override
    public void send(T exceptionNotice) {
        try {
            log.info("--------------->>>>>send<<<<<<<<-----------------------");
            String text = resolver.resolve(exceptionNotice);
            content = new DingContent();
            content.setContent(text);
            DingdingExceptionSendContext dingdingExceptionContext = dingDingProperty.createDingdingNotice(content);
            ((DingdingExceptionSendContext) dingdingExceptionContext).setText(content);
            client.doSend(dingdingExceptionContext);
        } catch (Exception e) {
            log.info(e.getCause().toString() + "\n \n");//TODO:加本项目特有的异常，然后用日志打印
            e.printStackTrace();
        }
    }
}
