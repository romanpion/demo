package com.romao.demo.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.TextView;

import com.romao.demo.R;
import com.romao.demo.controllers.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgressView extends BaseView {
    private String title;
    @BindView(R.id.title)
    TextView titleTxt;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onViewCreated(Bundle savedState) {
        super.onViewCreated(savedState);
        LayoutInflater.from(getContext()).inflate(R.layout.progress_view, this);
        ButterKnife.bind(this);
        titleTxt.setText(title);
    }

    @Override
    final public void showAsDialog() {
        showAsDialog(R.string.updating);
    }

    public void showAsDialog(int stringResId) {
        showAsDialog(getString(stringResId));
    }

    public void showAsDialog(String title) {
        this.title = title;
        super.showAsDialog(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                false);
    }
}
