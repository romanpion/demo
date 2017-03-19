package com.romao.demo.domain;

import com.romao.demo.network.Api;
import com.romao.demo.network.entities.ChangePasswordRequest;

import rx.Observable;
import rx.schedulers.Schedulers;

public class PasswordInteractor {

    private Api api;

    public PasswordInteractor(Api api) {
        this.api = api;
    }

    public Observable<Void> changePassword(ChangePasswordRequest request) {
        return api.changePassword(request).subscribeOn(Schedulers.newThread());
    }
}
