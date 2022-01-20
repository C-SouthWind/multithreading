package com.chj.gaoji;

/**
 * 生产者先假设为false ---》 开始生产 ---》 通知消费者  ---》 更改标识为true
 * 消费者为true  ---》 开始消费  ---》 通知生产者 --》更改标识为false
 */
//测试多线程通信 信号灯法
public class TestCS2 {
    public static void main(String[] args) {
        Hotel2 hotel2 = new Hotel2();
        new Cook2(hotel2).start();
        new People2(hotel2).start();
    }
}



//厨师
class Cook2 extends Thread{
    Hotel2 hotel2;
    public Cook2(Hotel2 hotel2){
        this.hotel2 = hotel2;
    }

    @Override
    public void run() {
        String[] split = hotel2.recipe.split(",");
        for (String s : split) {
            hotel2.makeRice(s);
        }
    }
}

//人员
class People2 extends Thread{
    Hotel2 hotel2;
    public People2(Hotel2 hotel2){
        this.hotel2 = hotel2;
    }
    @Override
    public void run() {
        String[] split = hotel2.recipe.split(",");
        for (String s : split) {
            hotel2.HaveAMeal();
        }
    }
}

//饭店
class Hotel2{
    String recipe = "红烧肉,米饭,青椒炒肉丝,烤红薯,糖葫芦,水煮肉片";
    String food;//食物
    boolean flag = false;//设立标识

    //做饭
    public synchronized void makeRice(String food){
        //饭店有饭
        if (flag) {
            try {
                //等待人们去吃
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //else  饭店没有饭 开始做饭
        System.out.println("厨师做了" + food);
        this.food = food;
        //通知他们去吃饭
        this.notifyAll();
        //标识改为true 有饭了
        this.flag = !this.flag;
    }

    //吃饭
    public synchronized String HaveAMeal(){
        //饭店没有饭
        if (!flag) {
            try {
                //等待厨师做饭
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //else  饭店有饭 开始吃饭
        System.out.println("人们吃了" + food);
        //通知做饭
        this.notifyAll();
        //标识改为false  没有饭了
        this.flag = !this.flag;
        return food;
    }

}
