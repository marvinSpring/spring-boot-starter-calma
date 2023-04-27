package com.marvin.factory;

import com.marvin.model.message.DingMessage;
import com.marvin.model.message.ding.DingAt;
import com.marvin.model.message.ding.DingDingProperty;
import com.marvin.model.message.ding.DingMarkdown;
import com.marvin.model.message.ding.DingText;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DingMessageFactory {

    /**
     * dingDingProperty and text -> to fill DingMessage
     * @param dingDingProperty dingDingProperty
     * @param text notify content
     * @return {@link DingMessage}
     */
    public static DingMessage createDingMessage(DingDingProperty dingDingProperty, String text){
        DingMessage dingMessage = null;
        boolean isMarkDown = false;
        boolean isText = false;
        if (!StringUtils.isEmpty(dingDingProperty.getMsgtype()) &&
                dingDingProperty.getMsgtype().equalsIgnoreCase("markdown")){
            DingMarkdown markdown = new DingMarkdown();
            markdown.setText(text);
            dingMessage = dingDingProperty.createDingdingMarkdownNotice(markdown);
            dingMessage.setMarkdown(markdown);
            isMarkDown = true;
        }
        if (!StringUtils.isEmpty(dingDingProperty.getMsgtype()) &&
                dingDingProperty.getMsgtype().equalsIgnoreCase("text")) {
            DingText content = new DingText();
            content.setContent(text);
            dingMessage = dingDingProperty.createDingdingTextNotice(content);
            dingMessage.setText(content);
            isText = true;
        }

        if (Objects.nonNull(dingMessage)){

            String phoneNumbers = dingDingProperty.getPhoneNumbers();
            String userIds = dingDingProperty.getUserIds();

            DingAt dingAt = DingAt.builder()
                            .isAtAll(dingDingProperty.isAtAll())
                            .atMobiles(!StringUtils.isEmpty(phoneNumbers)?dingDingProperty.getPhoneNumbers().split(","):null)
                            .atUserIds(!StringUtils.isEmpty(userIds)?dingDingProperty.getUserIds().split(","):null)
                            .build();
            dingMessage.setAt(dingAt);

            if (isMarkDown){
                DingMarkdown markdown = dingMessage.getMarkdown();
                if (Objects.nonNull(markdown)){
                    StringBuilder stringBuilder = new StringBuilder(markdown.getText());
                    if (!StringUtils.isEmpty(phoneNumbers)) {
                        Arrays.stream(phoneNumbers.split(",")).forEach(phone -> {
                            stringBuilder.append("\r\n").append("@").append(phone);
                        });
                    }
                    if (!StringUtils.isEmpty(userIds)) {
                        Arrays.stream(userIds.split(",")).forEach(userId -> {
                            stringBuilder.append("\r\n").append("@").append(userId);
                        });
                    }
                    markdown.setText(stringBuilder.toString());
                }
            }
            if (isText){
                DingText dingText = dingMessage.getText();
                if (Objects.nonNull(dingText)){
                    StringBuilder stringBuilder = new StringBuilder(dingText.getContent());
                    if (!StringUtils.isEmpty(phoneNumbers)) {
                        Arrays.stream(dingDingProperty.getPhoneNumbers().split(",")).forEach(phone -> {
                            stringBuilder.append("\r\n").append("@").append(phone);
                        });
                    }
                    if (!StringUtils.isEmpty(userIds)){
                        Arrays.stream(userIds.split(",")).forEach(userId->{
                            stringBuilder.append("\r\n").append("@").append(userId);
                        });
                    }

                    dingText.setContent(stringBuilder.toString());
                }
            }
        }

        return dingMessage;
    }

}
