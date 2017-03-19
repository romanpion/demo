package com.romao.demo.controllers.userprofile;

import android.net.Uri;

import com.romao.demo.R;
import com.romao.demo.application.BaseSubscriber;
import com.romao.demo.domain.PasswordInteractor;
import com.romao.demo.domain.UsersInteractor;
import com.romao.demo.model.entities.User;
import com.romao.demo.network.entities.ChangePasswordRequest;
import com.romao.demo.network.entities.UserUpdateRequest;
import com.romao.demo.utils.StringUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserProfilePresenter {

    private UserProfileView view;

    private UsersInteractor usersInteractor;

    private PasswordInteractor passwordInteractor;

    private Uri selectedCvFile;

    public UserProfilePresenter(UserProfileView view,
                                UsersInteractor usersInteractor,
                                PasswordInteractor passwordInteractor) {
        this.view = view;
        this.usersInteractor = usersInteractor;
        this.passwordInteractor = passwordInteractor;
    }

    public void onViewAttached() {
        User user = usersInteractor.getCurrentUser();
        if (user == null) {
            view.showToast("Something went wrong");
            return;
        }

        view.setFirstName(user.getFirstName());
        view.setLastName(user.getLastName());
        view.setMiddleInitial(user.getMiddleInitial());
        view.setEmail(user.getEmail());
        view.setPhoneNumber(user.getPhoneNumber());
        view.setEmployeeNumber(String.valueOf(user.getEmployeeNumber()));
        view.setJobTitle(user.getJobTitle());
        view.setDepartment(user.getCompany() != null ? user.getCompany().getName() : null);
        view.setOfficeLocation(user.getOfficeLocation());
        view.setLinkedInProfile(user.getLinkedInProfile());
    }

    private boolean validateChangePassword() {
        boolean result = true;

        if (StringUtils.isEmpty(view.getOldPassword())) {
            view.setOldPasswordError(R.string.validate_empty);
            result = false;
        }

        if (StringUtils.isEmpty(view.getNewPassword())) {
            view.setNewPasswordError(R.string.validate_empty);
            result = false;
        }

        if (StringUtils.isEmpty(view.getConfirmPassword())) {
            view.setConfirmPasswordError(R.string.validate_empty);
            result = false;
        }

        if (!StringUtils.isEmpty(view.getNewPassword())
                && view.getOldPassword().equals(view.getNewPassword())) {
            view.setNewPasswordError(R.string.user_profile_error_same_password);
            result = false;
        }

        if (!StringUtils.isEmpty(view.getConfirmPassword())
                && !StringUtils.isEmpty(view.getNewPassword())
                && !view.getNewPassword().equals(view.getConfirmPassword())) {
            view.setConfirmPasswordError(R.string.user_profile_error_password_dont_match);
            result = false;
        }

        return result;
    }

    public void onChangePasswordClicked() {
        if (!validateChangePassword()) {
            return;
        }

        ChangePasswordRequest request = new ChangePasswordRequest();
        request.oldPassword = view.getOldPassword();
        request.newPassword = view.getNewPassword();

        view.showProgress();

        passwordInteractor.changePassword(request)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(() -> view.hideProgress())
                .doOnError(throwable -> view.showToast(throwable.getMessage()))
                .subscribe(new BaseSubscriber<Void>() {
                    @Override
                    public void onNext(Void aVoid) {
                        view.showToast("Password changed");
                        view.resetPasswordFields();
                    }
                });
    }

    private boolean validateUserInfo() {
        boolean result = true;

        if (StringUtils.isEmpty(view.getFirstName())) {
            result = false;
            view.setFirstNameError(R.string.validate_empty);
        }

        if (StringUtils.isEmpty(view.getLastName())) {
            result = false;
            view.setLastNameError(R.string.validate_empty);
        }

        if (!StringUtils.matchEmailPattern(view.getEmail())) {
            result = false;
            view.setEmailError(R.string.validate_invalid_email);
        }

        return result;
    }

    public void onSaveUserInformationClicked() {
        if (!validateUserInfo()) {
            return;
        }

        User currentUser = usersInteractor.getCurrentUser();

        UserUpdateRequest request = new UserUpdateRequest();
        request.officeLocation = StringUtils.getNotEmptyOrNull(view.getOfficeLocation());
        request.phoneNumber = StringUtils.getNotEmptyOrNull(view.getPhoneNumber());
        request.jobTitle = StringUtils.getNotEmptyOrNull(view.getJobTitle());
        request.email = StringUtils.getNotEmptyOrNull(view.getEmail());
        request.employeeNumber = StringUtils.isEmpty(view.getEmployeeNumber()) ? null : Integer.parseInt(view.getEmployeeNumber());
        request.id = currentUser.getId();
        request.UserId = currentUser.getUserId();
        request.linkedinProfile = StringUtils.getNotEmptyOrNull(view.getLinkedinProfile());
        request.firstName = view.getFirstName();
        request.lastName = view.getLastName();
        request.middleInitial = StringUtils.getNotEmptyOrNull(view.getMiddleInitial());

        view.showProgress();

        usersInteractor.updateUser(request)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> view.showToast(throwable.getMessage()))
                .doOnTerminate(() -> view.hideProgress())
                .subscribe(new BaseSubscriber<User>() {
                    @Override
                    public void onNext(User user) {
                        view.showToast("User updated");
                        selectedCvFile = null;
                    }
                });
    }

    public void onSaveNotificationSettingsClicked() {
        // TODO
    }
}
