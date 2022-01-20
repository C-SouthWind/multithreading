package com.chj.syn;

//不安全的取钱
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(100,"招商");
        Drawing you = new Drawing(account, 50, "你");
        Drawing ta = new Drawing(account, 100, "它");
        you.start();
        ta.start();
    }
}

//账户
class Account{
    int money;//余额
    String name;//卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread{
     Account account;//账户
     int drawingMoney;//取了多少钱
     int nowMoney;//现在手里有多少钱

    public Drawing(Account account,int drawingMoney,String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    @Override
    public  void run() {
        //锁的对象就是变化的量，需要增删改的对象
        synchronized(account){
            //判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }
            //模拟延迟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 余额 - 你取的钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);

            //Thread.currentThread().getName() = this.getName()
            System.out.println(this.getName() + "手里的钱：" + nowMoney);
        }
    }
}