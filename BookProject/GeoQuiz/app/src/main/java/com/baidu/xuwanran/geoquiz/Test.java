package com.baidu.xuwanran.geoquiz;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xuwanran on 2018/7/20.
 */

public class Test {
    static class MyTimerTask extends TimerTask{
        @Override
        public void run() {

        }
    }
    public static void main(String args[]){
        ExecutorService threadpool = Executors.newFixedThreadPool(3);
    }
}
