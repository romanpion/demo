package com.romao.demo.model;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.j256.ormlite.support.ConnectionSource;
import com.romao.demo.model.entities.User;
import com.romao.demo.network.entities.UserResponse;
import com.romao.demo.utils.ModelFromResponseUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao<User, Integer> {
    private static final String TAG = UserDao.class.getName();

    private static final String AUTH_TOKEN = TAG + "AUTH_TOKEN";
    private static final String CURRENT_USER_COMPANY_ID = TAG + "CURRENT_USER_COMPANY_ID";
    private static final String USER_ID = TAG + "USER_ID";
    private SharedPreferences sharedPreferences;
    private CompanyDao companyDao;

    public UserDao(ConnectionSource connectionSource, SharedPreferences sharedPreferences, CompanyDao companyDao) throws SQLException {
        super(connectionSource, User.class);
        this.sharedPreferences = sharedPreferences;
        this.companyDao = companyDao;
    }

    public void saveAuthToken(String token) {
        sharedPreferences.edit().putString(AUTH_TOKEN, token).apply();
    }

    @Nullable
    public String getAuthToken() {
        return sharedPreferences.getString(AUTH_TOKEN, null);
    }

    public void saveUserId(int userId) {
        sharedPreferences.edit().putInt(USER_ID, userId).apply();
    }

    @Nullable
    public int getUserId() {
        return sharedPreferences.getInt(USER_ID, -1);
    }

    public void saveUser(UserResponse userResponse) {
        try {
            deleteById(userResponse.id);

            User user = ModelFromResponseUtils.userFromResponse(userResponse);
            createOrUpdate(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveAll(List<UserResponse> userResponses, boolean cleanData) {
        try {
            callBatchTasks(() -> {
                if (cleanData) {
                    cleanData();
                }

                for (UserResponse userResponse : userResponses) {
                    if(getUserId() != userResponse.id) {
                        saveUser(userResponse);
                    }
                }

                return null;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAllNoClean(List<UserResponse> userResponses) {
        saveAll(userResponses, false);
    }

    public void saveAll(List<UserResponse> userResponses) {
        saveAll(userResponses, true);
    }

    public List<User> getAll() {
        try {
            return queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public User getCurrentUser() {
        int userId = getUserId();

        try {
            //return queryBuilder().where().eq(User.ID_COLUMN, String.valueOf(userId)).queryForFirst();
            return queryForId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(int userId) {
        try {
            return queryForId(Integer.valueOf(userId));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
