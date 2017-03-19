package com.romao.demo.application;

import android.util.Log;


public class BaseSubscriber<T> extends rx.Subscriber<T> {
    private static final String TAG = BaseSubscriber.class.getName();

    @Override
    public void onCompleted() {
        // nothing by default.
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage(), e);
        // nothing by default.
    }

    @Override
    public void onNext(T t) {
        // nothing by default.
    }
}
