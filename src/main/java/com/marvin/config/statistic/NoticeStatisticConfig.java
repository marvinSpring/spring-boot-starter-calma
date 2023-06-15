//package com.marvin.config.statistic;
//
//import com.marvin.statistic.cache.StatisticCache;
//import com.marvin.util.NumberUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ConditionalOnProperty(name = "calma.exceptionnotice.statisticalDays")
//public class NoticeStatisticConfig {
//
//    @Value("${calma.exceptionnotice.statisticalDays}")
//    private String statisticalDays;
//
//    @Bean
//    public StatisticCache statisticCache() {
//        return new StatisticCache(NumberUtils.strToLong(statisticalDays, "calma.exceptionnotice.statisticalDays"));
//    }
//}
