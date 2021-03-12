package com.study.server.demo.designmodel.observer;

/**
 * @ClassName: Observer
 * @Description: 观察着接口
 * @Author: zhānghào
 * @Date: 2020/12/8 5:41 下午
 * @Version: v1.0
 **/
public interface Observer {

    /**
     * 当被观察发生改变的时候
     * 通知观察着作出响应
     *
     * @param message
     */
    void action(String message);
}
