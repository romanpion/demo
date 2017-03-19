package com.romao.demo.model;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import rx.Observable;

public abstract class BaseDao<T, ID> extends BaseDaoImpl<T, ID> {
    protected BaseDao(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public void cleanData() {
        try {
            TableUtils.clearTable(connectionSource, getDataClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Observable<T> refreshObservable(T data) {
        return Observable.fromCallable(() -> {
            refresh(data);

            return data;
        });
    }

    public Observable<Object> updateObservable(T data) {
        return Observable.fromCallable(() -> {
            update(data);

            return data;
        });
    }
}
