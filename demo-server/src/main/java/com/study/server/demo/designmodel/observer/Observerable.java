package com.study.server.demo.designmodel.observer;

/**
 * @ClassName: Observerable
 * @Description: 被观察着接口
 * @Author: zhānghào
 * @Date: 2020/12/8 5:39 下午
 * @Version: v1.0
 **/
public interface Observerable {

    /**
     * 注册观察着
     *
     * @param observer
     */
    void registerObserver(Observer ...observer);

    void removeObserver(Observer observer);

    /**
     * 通知观察着
     */
    void  notifyObserver();
}
