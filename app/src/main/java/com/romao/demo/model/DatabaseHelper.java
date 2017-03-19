package com.romao.demo.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.romao.demo.model.entities.Company;
import com.romao.demo.model.entities.Location;
import com.romao.demo.model.entities.ResourceType;
import com.romao.demo.model.entities.User;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final Class[] DB_ENTITIES = {
            User.class,
            Location.class,
            Company.class,
            ResourceType.class
    };

    public DatabaseHelper(Context context) throws PackageManager.NameNotFoundException {
        super(context,
                context.getApplicationContext().getPackageName() + ".db",
                null,
                context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            for (Class entity : DB_ENTITIES) {
                TableUtils.createTable(connectionSource, entity);
            }
        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + getDatabaseName());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer) {
        try {
            for (Class entity : DB_ENTITIES) {
                TableUtils.dropTable(connectionSource, entity, true);
            }
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        super.close();
    }

    public void clearDb() {
        try {
            for (Class entity : DB_ENTITIES) {
                TableUtils.clearTable(connectionSource, entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}