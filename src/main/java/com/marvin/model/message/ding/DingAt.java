package com.marvin.model.message.ding;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class DingAt {

    private String[] atMobiles;

    private String[] atUserIds;

    private boolean isAtAll;

}
