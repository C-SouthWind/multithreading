package com.chj.demo02;

import com.chj.demo01.TestThread2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//线程创建方式三：实现callable接口
public class TestCallable implements Callable<Boolean> {
    private String url;//网络图片地址
    private String name;//保存的文件名

    public TestCallable(String url,String name){
        this.url = url;
        this.name = name;
    }

    //下载图片线程的执行体
    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,name);
        System.out.println("下载了文件名为：" + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://t7.baidu.com/it/u=1819248061,230866778&fm=193&f=GIF","渐变风格插画1.jpg");
        TestCallable t2 = new TestCallable("https://t7.baidu.com/it/u=1297102096,3476971300&fm=193&f=GIF","渐变风格插画2.jpg");
        TestCallable t3 = new TestCallable("https://t7.baidu.com/it/u=4240641596,3235181048&fm=193&f=GIF","渐变风格插画3.jpg");

        ExecutorService ser = Executors.newFixedThreadPool(1);
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);
        Boolean b1 = r1.get();
        Boolean b2 = r2.get();
        Boolean b3 = r3.get();
        ser.shutdownNow();
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
