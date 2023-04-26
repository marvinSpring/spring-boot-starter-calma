package com.marvin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingMarkdown {

    public DingMarkdown() {
        this.title = "项目异常通知";
    }

    private String title;

    private String text;

}
