package com.url.edustatistics.schedule;

import com.url.edustatistics.service.StatisticsDailyService;
import com.url.edustatistics.utils.DateUtil;
import javafx.concurrent.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xidazhen
 * @date 2022/10/11 - 14:44
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    /**
     * cron 表达式 https://cron.qqe2.com/ 整理前一天的数据
     */
    @Scheduled(cron = "0 5 15 * * ?")
    public void task(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }
}
