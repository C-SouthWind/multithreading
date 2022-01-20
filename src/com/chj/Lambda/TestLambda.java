package com.chj.Lambda;

/**
 * 推导lambda表达式
 */
public class TestLambda {
    //3.静态内部类
    static class Like2 implements ILike{

        @Override
        public void lambda() {
            System.out.println("I Like Lambda2");
        }
    }


    public static void main(String[] args) {
        ILike iLike = new Like();
        iLike.lambda();

        iLike = new Like2();
        iLike.lambda();

        //4.局部内部类
        class Like3 implements ILike{

            @Override
            public void lambda() {
                System.out.println("I Like Lambda3");
            }
        }

        iLike = new Like3();
        iLike.lambda();

        //5.匿名内部类 没有类的名称，必须借助接口或者父类
        iLike = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I Like Lambda4");
            }
        };
        iLike.lambda();

        //6.用lambda简化
        iLike = () ->{
            System.out.println("I Like Lambda5");
        };
        iLike.lambda();
    }
}

//1.定义一个函数式接口
interface ILike{
    void lambda();
}
//2.实现类
class Like implements ILike{

    @Override
    public void lambda() {
        System.out.println("I Like Lambda");
    }
}