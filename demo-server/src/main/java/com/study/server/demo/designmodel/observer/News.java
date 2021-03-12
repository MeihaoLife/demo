package com.study.server.demo.designmodel.observer;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: WechatObserverable
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/8 5:47 下午
 * @Version: v1.0
 **/
public class News implements Observerable{

    private List<Observer> observers;

    private String news;

    public void publishMessage(String message) {
        this.news = message;
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer ...observer) {
        if (CollectionUtils.isEmpty(observers)) {
            observers = new ArrayList<>();
        }
        observers.addAll(Arrays.asList(observer));
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        observers.parallelStream().forEach(o -> o.action(news));
    }
}
