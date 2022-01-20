package com.chj.thread;

import com.chj.demo01.TestThread2;

import java.util.concurrent.locks.ReentrantLock;

//测试Lock锁
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testThread2 = new TestLock2();
        new Thread(testThread2).start();
        new Thread(testThread2).start();
        new Thread(testThread2).start();

    }

}

class TestLock2 implements Runnable{
    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();


    byte ticketNums = 10;

    @Override
    public void run() {
        while (true){
            try {
                lock.lock(); //加锁
                if (ticketNums > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNums--);
                } else {
                    break;
                }
            }finally {
                lock.unlock();//释放锁
            }
        }
    }
}
