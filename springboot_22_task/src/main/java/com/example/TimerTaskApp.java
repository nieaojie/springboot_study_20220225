package com.example;

import java.util.Timer;
import java.util.TimerTask;

/**
 * java定时任务的简单实现
 */
public class TimerTaskApp {
    public static void main(String[] args) {
        final int[] times = { 0 };
        Timer timer = new Timer();
        //指定一个定时任务，从运行时开始(不延时)，每隔2s运行一次
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (times[0] == 3) {
                    timer.cancel();
                    System.out.println("定时任务取消了");
                }
                System.out.println("timeTask run...");
                times[0]++;
            }
        };
        timer.schedule(task, 0, 2000);
    }
}
