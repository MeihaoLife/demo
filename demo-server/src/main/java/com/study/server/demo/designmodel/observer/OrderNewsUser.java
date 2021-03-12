package com.study.server.demo.designmodel.observer;

import lombok.Data;

/**
 * @ClassName: OrderNewsUser
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/8 5:51 下午
 * @Version: v1.0
 **/
@Data
public class OrderNewsUser implements Observer {

    private String userName;

    private String userId;

    @Override
    public void action(String message) {
        System.out.printf("user = %s, receive message = %s", this, message);
        System.out.println();
    }
}
