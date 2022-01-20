package com.chj.StaticProxy.java;

/**
 * 静态代理模式总结
 * 真是对象和代理对象都要实现同一个接口
 * 代理对象要带真是角色
 *
 * 好处：
 *      代理对象可以做很多真实对象做不了的事情
 *      真实对象专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args) {
        new Thread( () -> System.out.println("代理模式")).start();
        new WeddingCompany(new You()).HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真是角色，你去结婚
class You implements Marry{

    @Override
    public void HappyMarry() {
        System.out.println("结婚");
    }
}

//代理角色，帮助结婚
class WeddingCompany implements Marry{
    //代理谁---》真是角色
    private Marry target;

    public WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();//这就是真是对象
        after();
    }

    private void after() {
        System.out.println("结婚后");
    }

    private void before() {
        System.out.println("结婚前");
    }
}
