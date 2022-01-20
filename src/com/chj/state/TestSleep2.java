package com.chj.state;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException {
       //打印当前系统时间
        Date date = new Date(System.currentTimeMillis());//获取系统当前时间
        while (true){
            Thread.sleep(1000);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
            date = new Date(System.currentTimeMillis());//更新系统当前时间
        }
    }


    public static void tenDown() throws InterruptedException {

        int num = 10;
        while (true){
            Thread.sleep(1000);
            System.out.println(num--);
            if (num<=0) {
                break;
            }
        }
    }
}
