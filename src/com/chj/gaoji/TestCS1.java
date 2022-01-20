package com.chj.gaoji;

/**
 * 生产者先生产一个  消费才能取出  所以是生产者先加加 再通知
 * 消费者先拿走   才能进行消费   所以消费者是先减减   再通知
 *
 */
//测试多线程通信 程管法
public class TestCS1 {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        new Cook(hotel).start();
        new People(hotel).start();
    }
}


//厨师
class Cook extends Thread{
    Hotel hotel;
    public Cook(Hotel hotel){
        this.hotel = hotel;
    }

    @Override
    public void run() {
        String[] split = hotel.recipe.split(",");
        for (String s : split) {
            hotel.makeRice(s);
        }
    }
}

//人员
class People extends Thread{
    Hotel hotel;
    public People(Hotel hotel){
        this.hotel = hotel;
    }

    @Override
    public void run() {
        String[] split = hotel.recipe.split(",");
        for (String s : split) {
            hotel.HaveAMeal();
        }
    }
}

//饭店
class Hotel{
    String recipe = "红烧肉,米饭,青椒炒肉丝,烤红薯,糖葫芦,水煮肉片";
    String[] traffics = new String[2];//饭店最多人数
    int count = 0;//当前多少人

    //做饭
    public synchronized void makeRice(String food){
        //人满了
        if (count == traffics.length) {
            try {
                //等待
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //食物放到餐厅里
        traffics[count] = food;
        System.out.println("厨师做了" + food);
        //人数+1
        count++;
        //通知吃饭
        this.notifyAll();
    }

    //吃饭
    public synchronized String HaveAMeal(){
        //没有饭
        if (count == 0) {
            try {
                //等待厨师做饭
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //人数-1
        count--;
        //吃到的食物
        String traffic = traffics[count];
        System.out.println("人们吃到了" + traffic);
        //通知厨师继续做饭
        this.notifyAll();
        return traffic;
    }

}