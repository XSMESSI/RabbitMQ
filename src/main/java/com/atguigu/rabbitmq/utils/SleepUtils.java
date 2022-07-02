package com.atguigu.rabbitmq.utils;

/**
 * @Author: Leo messi
 * @Date: 2022/07/02/16:45
 * @Description:
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
    } }
