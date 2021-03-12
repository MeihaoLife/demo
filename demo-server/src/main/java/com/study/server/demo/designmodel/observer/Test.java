package com.study.server.demo.designmodel.observer;

/**
 * @ClassName: Tes
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/8 5:53 下午
 * @Version: v1.0
 **/
public class Test {

    public static void main(String[] args) {

        OrderNewsUser u1 = new OrderNewsUser();
        u1.setUserId("1");
        u1.setUserName("u1");

        OrderNewsUser u2 = new OrderNewsUser();
        u2.setUserId("2");
        u2.setUserName("u2");

        OrderNewsUser u3 = new OrderNewsUser();
        u3.setUserId("3");
        u3.setUserName("u3");

        News news = new News();
        news.registerObserver(u1, u2, u3);

        news.publishMessage("Hello Observer!");
    }

}
