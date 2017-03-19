package com.romao.demo.controllers;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.romao.demo.R;
import com.romao.demo.application.App;
import com.romao.demo.dialogs.ProgressView;
import com.romao.demo.network.ApiProvider;

import retrofit2.adapter.rxjava.HttpException;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressView progressView;
    private int progressCount;

    private boolean isActivityDead;
    private boolean isActivityResumed;

    private Toolbar toolbar;
    private TextView titleTextView;
    private ViewGroup customViewContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        progressView = new ProgressView(this, null);
    }

    @Override
    public void setContentView(int layoutResId) {
        View childView = View.inflate(this, layoutResId, null);

        setContentView(childView);
    }

    @Override
    public void setContentView(View view) {
        ViewGroup rootView = (ViewGroup) View.inflate(this, R.layout.base_activity, null);

        toolbar = (Toolbar) rootView.findViewById(R.id.base_activity_toolbar);
        toolbar.setTitle(null);
        titleTextView = (TextView) toolbar.findViewById(R.id.base_activity_toolbar_title);
        customViewContainer = (ViewGroup) toolbar.findViewById(R.id.base_activity_toolbar_custom_view);
        progressView = new ProgressView(this, null);

        ViewGroup childActivityContainer = (ViewGroup) rootView.findViewById(R.id.child_activity_content);
        childActivityContainer.addView(view);

        super.setContentView(rootView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActivityResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideProgress();
        isActivityResumed = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActivityDead = true;
    }

    public void showProgress() {
        showProgress(R.string.updating);
    }

    public void showProgress(int stringResId) {
        showProgress(getString(stringResId));
    }

    public void showProgress(String string) {
        if (null == progressView.getDialog()
                || !progressView.getDialog().isShowing()) {
            progressView.showAsDialog(string);
        }
        progressCount++;
    }

    public void hideProgress() {
        progressCount = progressCount > 0 ? progressCount - 1 : 0;
        if (progressCount == 0 && progressView.isDialog()) {
            progressView.dismiss();
        }
    }

    public void showError(Throwable e) {
        if (HttpException.class.isInstance(e)) {
            HttpException httpException = (HttpException) e;
            if (ApiProvider.CODE_UNAUTHORIZED == httpException.code()) {
                finish();
            }
        }
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    public boolean isActivityDead() {
        return isActivityDead;
    }

    public boolean isActivityResumed() {
        return isActivityResumed;
    }

    private BaseActivityComponent baseActivityComponent;

    public App getApp() {
        return (App) getApplication();
    }

    public BaseActivityComponent getBaseActivityComponent() {
        if (null == baseActivityComponent) {
            baseActivityComponent = getApp().getAppComponent()
                    .baseActivityComponent(new BaseActivityModule(this));
        }

        return baseActivityComponent;
    }

    public Toolbar getToolbar() {

        return toolbar;
    }

    @Override
    public void setTitle(int titleRes) {
        setTitle(getText(titleRes));
    }

    @Override
    public void setTitle(CharSequence title) {

        titleTextView.setText(title);
    }

    public void setDisplayHomeAsUpEnabled() {
        toolbar.setNavigationIcon(R.drawable.ic_back_toolbar);
        toolbar.setNavigationOnClickListener(v -> {
            supportFinishAfterTransition();
        });
    }

    public void setCustomView(View view) {
        customViewContainer.removeAllViews();
        if (null == view) {
            customViewContainer.setVisibility(View.GONE);

        } else {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER_VERTICAL);
            int margin = getResources().getDimensionPixelSize(R.dimen.custom_action_bar_item_margin);
            layoutParams.setMargins(margin, 0, 0, 0);

            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view, layoutParams);
        }
    }
}
