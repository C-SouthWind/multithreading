package com.chj.syn;

import jdk.nashorn.internal.ir.CallNode;

//不安全的买票
//线程不安全
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket,"小明").start();
        new Thread(buyTicket,"小张").start();
        new Thread(buyTicket,"小李").start();
    }
}


class BuyTicket implements Runnable{

    //票
    private int ticketNums = 10;
    boolean flag = true;//外部停止方式

    @Override
    public void run() {
        //买票
        while (flag){
            buy();
        }
    }

    //synchronized 同步方法，锁的是this
    private synchronized void buy(){
        //判断是否有票
        if (ticketNums<=0) {
            flag = false;
            return;
        }
        //模拟延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //买到
        System.out.println(Thread.currentThread().getName()+"拿到了第"+ticketNums--+"张票");
    }
}
