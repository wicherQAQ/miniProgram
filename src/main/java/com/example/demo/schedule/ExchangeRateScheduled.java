package com.example.demo.schedule;

import com.example.demo.service.ExchangeRateService;
import com.example.demo.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

import static com.example.demo.constant.ExchangeRateConstant.EXCHANGE_RATE_KEY;


@Configuration
@EnableScheduling
public class ExchangeRateScheduled {

    /**
     * 基货币
     */
    @Value("${fly.now-api.scur}")
    private String scur;

    /**
     * 目标货币
     */
    @Value("${fly.now-api.tcur}")
    private String tcur;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private RedisUtil redisUtil;

    //3.添加定时任务
    @Scheduled(cron = "0 0/3 * * * ?")
    private void configureTasks() {
        System.err.println("【汇率同步任务】执行静态定时任务时间: " + LocalDateTime.now());
        try{
            String data = exchangeRateService.getExchangeData(scur, tcur);
            redisUtil.set(EXCHANGE_RATE_KEY,data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
