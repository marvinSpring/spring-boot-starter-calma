package com.marvin.statistic.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@Accessors(chain = true)
public class ExceptionStatisticDto {

    private Throwable throwable;

    //当前异常出现频率
    private BigDecimal frequency;

    //当前异常出现次数
    private long count;

    //当前异常第一次出现时间
    private LocalDateTime firstAppearTime;

}
