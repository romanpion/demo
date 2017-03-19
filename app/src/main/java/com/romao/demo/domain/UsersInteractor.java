package com.romao.demo.domain;

import com.romao.demo.application.BaseSubscriber;
import com.romao.demo.model.UserDao;
import com.romao.demo.model.entities.User;
import com.romao.demo.network.Api;
import com.romao.demo.network.entities.UserRequest;
import com.romao.demo.network.entities.UserResponse;
import com.romao.demo.network.entities.UserUpdateRequest;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

public class UsersInteractor {

    private UserDao userDao;

    private Api api;

    public UsersInteractor(Api api, UserDao userDao) {
        this.api = api;
        this.userDao = userDao;
    }

    public Observable<List<User>> getUsers() {
        return Observable.fromCallable(() -> userDao.getAll());
    }

    public void refreshCompanyUsers() {
        User user = userDao.getCurrentUser();
        if (user == null) {
            return;
        }

        UserRequest request = new UserRequest();
        request.companyId = user.getCompanyId();
        api.getUsers(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.computation())
                .subscribe(new BaseSubscriber<List<UserResponse>>() {
                    @Override
                    public void onNext(List<UserResponse> userResponses) {
                        userDao.saveAllNoClean(userResponses);
                    }
                });
    }

    public int getCurrentUserId() {
        return userDao.getUserId();
    }

    public User getCurrentUser() {
        return userDao.getCurrentUser();
    }

    public Observable<User> updateUser(UserUpdateRequest userRequest) {
        return api.updateUser(userRequest).flatMap(aVoid -> getUser(userRequest.UserId));
    }

    public Observable<User> getUser(int userId) {
        return api.getUser(userId).map(userResponse -> {
            userDao.saveUser(userResponse);
            return userDao.getUserById(userId);
        });
    }
}
