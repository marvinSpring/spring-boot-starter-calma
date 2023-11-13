package com.marvin.statistic.cache;

import com.marvin.model.notice.CommonNotice;
import com.marvin.statistic.dto.ExceptionStatisticDto;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class StatisticHelper {

    /**
     * 异常持续时长
     */
    private final long keepDuration;

    public StatisticHelper(long keepDuration) {
        this.keepDuration = keepDuration;
    }

    /**
     * Exception all place name，val：dto
     */
    private static final Map<String, ExceptionStatisticDto> CACHE = new ConcurrentHashMap<>();

//    private static final Map<String, ExceptionStatisticDto> HTTP = new ConcurrentHashMap<>();

//    private static final ThreadLocal<Map<String, CommonNotice>> MICRO_SERVICE = new ThreadLocal<>();

    /**
     * 按指定天 统计异常
     * @param key 异常名称
     * @return 异常dto
     */
    public static ExceptionStatisticDto get(String key){
        return CACHE.get(key);
    }

    public static void common(CommonNotice commonNotice) {
        String className = commonNotice.getE().getClass().getName();
        CACHE.put(className, statisticCommon(className, commonNotice));
        commonNotice.setExceptionStatisticDto(StatisticHelper.get(commonNotice.getE().getClass().getName()));
    }

    /**
     * 统计通用通知信息
     * @param className 类名称
     * @param commonNotice 通用通知
     * @return 统计dto
     */
    private static ExceptionStatisticDto statisticCommon(String className, CommonNotice commonNotice) {
        ExceptionStatisticDto.ExceptionStatisticDtoBuilder builder = ExceptionStatisticDto.builder();
        ExceptionStatisticDto exceptionStatisticDto = CACHE.get(className);
        long count = CACHE.values().stream().mapToLong(ExceptionStatisticDto::getCount).sum();
        BigDecimal frequency;
        if (Objects.isNull(exceptionStatisticDto)) {
            builder.firstAppearTime(commonNotice.getCreateTime());
            builder.count(1);
            frequency = new BigDecimal(1).divide(new BigDecimal(count+1), 2, RoundingMode.HALF_UP);
        }else {
            builder.firstAppearTime(exceptionStatisticDto.getFirstAppearTime());
            builder.count(exceptionStatisticDto.getCount()+1);
            frequency = new BigDecimal(exceptionStatisticDto.getCount()+1).divide(new BigDecimal(count+1), 2, RoundingMode.HALF_UP);
        }
        frequency = frequency.multiply(new BigDecimal(100));
        builder.frequency(frequency);
        builder.throwable(commonNotice.getE());
        return builder.build();
    }

}
