package com.chj.gaoji;

//测试生产者和消费者
public class TestSX {
    public static void main(String[] args) {
       /* String[] a = new String[5];
        a[0] = "小1";
        a[1] = "小2";
        a[2] = "小3";
        a[3] = "小4";
        a[4] = "小5";
        System.out.println(a.length);
        String s = a[1];
        System.out.println(s);*/
        TheBuffer theBuffer = new TheBuffer();
        new Producers(theBuffer).start();
        new Consumers(theBuffer).start();

    }
}
//生产者
class Producers extends Thread{
    TheBuffer theBuffer;
    public Producers(TheBuffer theBuffer){
        this.theBuffer = theBuffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            theBuffer.push(new Product(i));
            System.out.println("生产了"+i+"个");
        }
    }
}
//消费者
class Consumers extends Thread{
    TheBuffer theBuffer;
    public Consumers(TheBuffer theBuffer){
        this.theBuffer = theBuffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Product pop = theBuffer.pop();
            if (pop != null) {
                System.out.println("消费了"+pop.product+"个");
            }else {
                System.out.println("消费完毕改生产了");
            }
        }
    }
}

//产品
class Product{
    int product;//产品编号
    public Product(int product){
        this.product = product;
    }
}

class TheBuffer{
    Product[] products = new Product[10];//缓冲池  池中可以存放10个
    int count = 0;//当前数量

    //生产

    /**
     * count = 10  通知消费
     * products[count] = product;  当count=9时存入
     * count++;  count = 10;  满足通知消费逻辑  但是数组中最大坐标其实是9
     */
    public synchronized void push(Product product){
        //池子满了 等待消费
        if (count == products.length) {
            try {
                System.out.println("该消费了");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            //池子没有满 开始生产
            products[count] = product;
            count++;
            //通知消费
            this.notifyAll();
    }

    //消费
    /**
     * count = 0  通知生产
     * count--; 因为生产时 最大坐标虽然是9  但是先存后++的 所有现在count是10  先-- 再根据坐标取数组值
     * Product product = products[count]; 第一次取出的数组坐标为9 最后一次取出的为0
     */
    public synchronized Product pop(){
        //池子没有东西，等待生产
        if (count == 0) {
            try {
                System.out.println("该生产了");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Product product = products[count];

        this.notifyAll();
        return product;
    }
}