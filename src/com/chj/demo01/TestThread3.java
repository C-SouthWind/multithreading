package com.chj.demo01;

//创建线程的方式2：实现runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，调用start方法
public class TestThread3 implements Runnable{
    @Override
    public void run() {
        //run方法线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("我是run方法线程体---"+i);
        }
    }

    public static void main(String[] args) {
      //创建runnable接口的实现类对象
        TestThread3 testThread3 = new TestThread3();
        //创建线程对象，通过线程对象来开启我们的线程，代理
        Thread thread = new Thread(testThread3);
        thread.start();

        //main线程，主线程
        for (int i = 0; i < 200; i++) {
            System.out.println("我是main方法线程体---"+i);
        }
    }
}
