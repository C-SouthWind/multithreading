package com.chj.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//联系Thread，实现多线程同步下载图片
public class TestThread2 extends Thread{
    private String url;//网络图片地址
    private String name;//保存的文件名

    public TestThread2(String url,String name){
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为：" + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF","渐变风格插画1.jpg");
        TestThread2 t2 = new TestThread2("https://t7.baidu.com/it/u=1297102096,3476971300&fm=193&f=GIF","渐变风格插画2.jpg");
        TestThread2 t3 = new TestThread2("https://t7.baidu.com/it/u=4240641596,3235181048&fm=193&f=GIF","渐变风格插画3.jpg");

        t1.start();
        t2.start();
        t3.start();
    }
}

//下载器
class WebDownloader{

    //下载方法
    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("io异常,downloader方法出现问题");
        }
    }
}
