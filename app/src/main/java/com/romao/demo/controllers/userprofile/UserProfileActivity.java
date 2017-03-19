package com.romao.demo.controllers.userprofile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.romao.demo.controllers.BaseActivity;

public class UserProfileActivity extends BaseActivity {

    private UserProfileView view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new UserProfileView(this);
        setContentView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static Intent getStartActivityIntent(Context context) {
        return new Intent(context, UserProfileActivity.class);
    }
}
