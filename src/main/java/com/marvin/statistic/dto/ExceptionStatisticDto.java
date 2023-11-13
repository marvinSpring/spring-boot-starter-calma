package com.marvin.statistic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 异常统计dto
 * @author marvin
 */
@Data
@Builder
@Accessors(chain = true)
public class ExceptionStatisticDto {

    /**
     * 异常
     */
    private Throwable throwable;

    /**
     * 当前异常出现频率
     */
    private BigDecimal frequency;

    /**
     * 当前异常出现次数
     */
    private long count;

    /**
     * 当前异常第一次出现时间
     */
    private LocalDateTime firstAppearTime;

}
