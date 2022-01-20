package com.chj.state;

//测试stop

/**
 * 建议线程正常停止---》利用次数，不建议死循环
 * 建议使用标志位---》设置一个标志位
 * 不要使用stop或者destroy等过时或者JDK不建议使用的方法
 */
public class TestStop implements Runnable{
    //设置一个标识位
    private Boolean flag = true;

    @Override
    public void run() {
        while (flag){
            int i = 0;
            System.out.println("run........Thread" + i++);
        }
    }

    //设置一个公开的方法停止线程，转换标志位
    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            if (i == 900) {
                //调用stop方法切换标志位，让线程停止
                testStop.stop();
                System.out.println("线程该停止了");
            }
        }
    }
}
