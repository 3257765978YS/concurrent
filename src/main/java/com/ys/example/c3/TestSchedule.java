package com.ys.example.c3;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author 杨帅
 * @Date 2022/5/18 7:36
 * @Version 1.0
 **/
@Slf4j
public class TestSchedule {
    //如何让每周四 18:00:00 定时执行任务
    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();//获取当前时间
        log.debug("{}",now);
        //获取周四时间
        LocalDateTime time = now.withHour(8).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.WEDNESDAY);
        //如果当前时间 > 本周周四，必须找到下周周四
        if(now.compareTo(time)>0){
            time = time.plusWeeks(1);
        }
        log.debug("{}",time);

        //initalDelay代表当前时间和周四时间差值
        //period 一周间隔
        long initalDelay = Duration.between(now, time).toMillis();
        long period = 1000;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(()->{
            log.debug("running...");
        },initalDelay,period, TimeUnit.MILLISECONDS);
    }
}
