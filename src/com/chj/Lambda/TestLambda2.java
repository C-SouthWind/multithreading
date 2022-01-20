package com.chj.Lambda;

public class TestLambda2 {

    static class Love2 implements ILove{

        @Override
        public void love(String name) {
            System.out.println("I  Love  " + name);
        }
    }

    public static void main(String[] args) {
        ILove love = new Love();
        love.love("1");

        love = new Love2();
        love.love("2");

        class Love3 implements ILove{

            @Override
            public void love(String name) {
                System.out.println("I  Love  " + name);
            }
        }
        love = new Love3();
        love.love("3");

        love = new ILove() {
            @Override
            public void love(String name) {
                System.out.println("I  Love  " + name);
            }
        };
        love.love("4");


        love = (String name) ->{
            System.out.println("I  Love  " + name);
        };
        //简化lambda表达式1  去掉参数类型
        love = (name) ->{
            System.out.println("I  Love  " + name);
        };
        //简化lambda表达式2  去掉括号
        love = name->{
            System.out.println("I  Love  " + name);
        };
        //简化lambda表达式3  去掉花括号
        love = name->System.out.println("I  Love  " + name);

        //总结
        /**
         * lambda表达式只能有一行代码的情况下才能简化成为一行，如果有多行，那么就用代码块包裹
         * 前提是接口为函数表达式接口（接口中只有一个方法）
         * 多个参数也可以去掉参数类型，要去掉就都去掉，必须加上括号
         */
        love.love("5");
    }
}


interface ILove{
    void love(String name);
}

class Love implements ILove{

    @Override
    public void love(String name) {
        System.out.println("I  Love  " + name);
    }
}
