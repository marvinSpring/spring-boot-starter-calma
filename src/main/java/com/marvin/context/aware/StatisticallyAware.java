package com.marvin.context.aware;

import com.marvin.model.notice.CommonNotice;

/**
 * 统计策略组件
 * @author marvin
 */
public interface StatisticallyAware {

    /**
     * 统计策略能力
     * @param notice 通知
     */
    void statistic(CommonNotice notice);

}
