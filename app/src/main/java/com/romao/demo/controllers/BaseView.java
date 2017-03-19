package com.romao.demo.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

public abstract class BaseView extends FrameLayout {
    private boolean isFirstTimeWindowVisible = true;
    private Bundle viewRestoredState;
    private boolean isStarted;

    private Dialog dialog;

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        if (visibility == VISIBLE) {
            if (isFirstTimeWindowVisible) {
                onViewCreated(viewRestoredState);
                isFirstTimeWindowVisible = false;
            }
            onStart();
            isStarted = true;
        } else {
            onStop();
            isStarted = false;
        }
    }


    @Override
    public final SavedState onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState state = new SavedState(superState);
        state.viewState = onSaveBaseViewInstanceState();

        return state;
    }

    protected Bundle onSaveBaseViewInstanceState() {
        return new Bundle();
    }


    @Override
    final public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof SavedState) {
            SavedState savedState = (SavedState) state;

            viewRestoredState = savedState.viewState;

            state = savedState.getSuperState();
        }
        super.onRestoreInstanceState(state);
    }

    public void onViewCreated(Bundle savedState) {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public BaseActivity getBaseActivity() {
        return getBaseActivity(getContext());
    }

    public BaseActivity getBaseActivity(Context context) {
        if (null == context) {
            return null;
        } else if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        } else if (context instanceof ContextWrapper) {
            return getBaseActivity(((ContextWrapper) context).getBaseContext());
        }

        return null;
    }

    public void setLayoutWidthHeight(int width, int height) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }

    @NonNull
    public String getString(@StringRes int stringRes) throws Resources.NotFoundException {
        return getResources().getString(stringRes);
    }

    @NonNull
    public String getString(@StringRes int id, Object... formatArgs) throws Resources.NotFoundException {
        return getResources().getString(id, formatArgs);
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isDialog() {
        return (null != dialog);
    }

    public void showAsDialog() {
        showAsDialog(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                true);
    }

    public void showAsDialog(int width, int height, boolean cancelableOnTouchOutside) {
        if (null == getBaseActivity() || getBaseActivity().isActivityDead()) {
            return;
        }
        if (ViewGroup.class.isInstance(getParent())) {
            ViewGroup parent = (ViewGroup) getParent();
            parent.removeAllViews();
        }

        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(this);
        dialog.setCanceledOnTouchOutside(cancelableOnTouchOutside);
        dialog.setOnDismissListener(BaseView.this::onDismiss);
        dialog.show();

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        if (null != window) {
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.width = width;
            layoutParams.height = height;
            window.setAttributes(layoutParams);
        }

        setLayoutWidthHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
    }

    protected void onDismiss(DialogInterface dialogInterface) {
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void dismiss() {
        if (null == dialog) {
            throw new UnsupportedOperationException("This view was not shown as a dialog. You should call BaseDialogView.showAsDialog() first");
        } else {
            dialog.dismiss();
        }
    }

    public void showProgress() {
        if (null != getBaseActivity() && !getBaseActivity().isActivityDead()) {
            getBaseActivity().showProgress();
        }
    }

    public void hideProgress() {
        if (null != getBaseActivity() && !getBaseActivity().isActivityDead()) {
            getBaseActivity().hideProgress();
        }
    }

    public void showError(Throwable e) {
        if (null != getBaseActivity() && !getBaseActivity().isActivityDead()) {
            getBaseActivity().showError(e);
        }
    }

    public void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showToast(@StringRes int textResId) {
        showToast(getString(textResId));
    }


    /**
     * the wrapper for saving view state as a bundle.
     */
    public static class SavedState extends BaseSavedState {
        Bundle viewState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.viewState = in.readBundle(getClass().getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBundle(this.viewState);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}