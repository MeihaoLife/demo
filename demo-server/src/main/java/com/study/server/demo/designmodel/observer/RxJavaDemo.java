package com.study.server.demo.designmodel.observer;

import io.reactivex.*;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName: RxJavaDemo
 * @Description: TODO
 * @Author: zhānghào
 * @Date: 2020/12/8 6:40 下午
 * @Version: v1.0
 **/
public class RxJavaDemo {

    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                observableEmitter.onNext(1);
                observableEmitter.onNext(2);
                observableEmitter.onNext(3);
                observableEmitter.onNext(4);
                observableEmitter.onComplete();
            }
        })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {

            Disposable disposable = null;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                System.out.println("订阅成功！");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("处理消息 message = " + integer);
                if (integer == 2) {
                    // 取消订阅，后续事件将不会再收到
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {
                System.out.println("处理完成");
            }
        });
    }
}
