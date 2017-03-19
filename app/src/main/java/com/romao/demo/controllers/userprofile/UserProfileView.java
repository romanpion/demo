package com.romao.demo.controllers.userprofile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.romao.demo.R;
import com.romao.demo.controllers.BaseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileView extends BaseView {

    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.middle_initial)
    EditText middleInitial;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.employee_number)
    EditText employeeNumber;
    @BindView(R.id.job_title)
    EditText jobTitle;
    @BindView(R.id.department)
    EditText department;
    @BindView(R.id.office_location)
    EditText officeLocation;
    @BindView(R.id.linkedin_profile)
    EditText linkedInProfile;

    @BindView(R.id.proposals_on_ads)
    CheckBox proposalsCheckbox;
    @BindView(R.id.responses_on_proposals)
    CheckBox responsesCheckbox;
    @BindView(R.id.inbox_messages)
    CheckBox inboxMessagesCheckbox;
    @BindView(R.id.comments_on_publications)
    CheckBox commentsCheckbox;
    @BindView(R.id.partner_publications)
    CheckBox partnerPublicationsCheckbox;
    @BindView(R.id.requests_from_colleagues)
    CheckBox colleagueRequestsCheckbox;
    @BindView(R.id.project_resource_or_assignment)
    CheckBox projectsPostedCheckbox;

    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.confirm_new_password)
    EditText confirmNewPassword;
    @BindView(R.id.save_notification_settings)
    Button saveNotificationSettingsButton;
    @BindView(R.id.save_user_info)
    Button saveUserInformationButton;
    @BindView(R.id.change_password)
    Button changePasswordButton;

    private UserProfilePresenter presenter;

    public UserProfileView(Context context) {
        super(context, null);

        UserProfileComponent component = getBaseActivity().getBaseActivityComponent()
                .userProfileComponent(new UserProfileModule(this));
        presenter = component.userProfilePresenter();
    }

    @Override
    public void onViewCreated(Bundle savedState) {
        super.onViewCreated(savedState);

        inflate(getContext(), R.layout.user_profile, this);
        ButterKnife.bind(this);

        presenter.onViewAttached();

        changePasswordButton.setOnClickListener(v -> presenter.onChangePasswordClicked());
        saveNotificationSettingsButton.setOnClickListener(v -> presenter.onSaveNotificationSettingsClicked());
        saveUserInformationButton.setOnClickListener(v -> presenter.onSaveUserInformationClicked());
    }

    public void setFirstName(String value) {
        firstName.setText(value);
    }

    public void setLastName(String value) {
        lastName.setText(value);
    }

    public void setMiddleInitial(String value) {
        middleInitial.setText(value);
    }

    public void setEmail(String value) {
        email.setText(value);
    }

    public void setPhoneNumber(String value) {
        phoneNumber.setText(value);
    }

    public void setEmployeeNumber(String value) {
        employeeNumber.setText(value);
    }

    public void setJobTitle(String value) {
        jobTitle.setText(value);
    }

    public void setDepartment(String value) {
        department.setText(value);
    }

    public void setOfficeLocation(String value) {
        officeLocation.setText(value);
    }

    public void setLinkedInProfile(String value) {
        linkedInProfile.setText(value);
    }

    public String getFirstName() {
        return firstName.getText().toString();
    }

    public String getLastName() {
        return lastName.getText().toString();
    }

    public String getMiddleInitial() {
        return middleInitial.getText().toString();
    }

    public String getEmail() {
        return email.getText().toString();
    }

    public String getJobTitle() {
        return jobTitle.getText().toString();
    }

    public String getOfficeLocation() {
        return officeLocation.getText().toString();
    }

    public String getEmployeeNumber() {
        return employeeNumber.getText().toString();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText().toString();
    }

    public String getDepartment() {
        return department.getText().toString();
    }

    public String getOldPassword() {
        return password.getText().toString();
    }

    public String getNewPassword() {
        return newPassword.getText().toString();
    }

    public String getConfirmPassword() {
        return confirmNewPassword.getText().toString();
    }

    public String getLinkedinProfile() {
        return linkedInProfile.getText().toString();
    }

    public void setFirstNameError(String error) {
        firstName.setError(error);
    }

    public void setFirstNameError(@StringRes int errorResId) {
        firstName.setError(getContext().getString(errorResId));
    }

    public void setLastNameError(String error) {
        lastName.setError(error);
    }

    public void setLastNameError(@StringRes int errorResId) {
        lastName.setError(getContext().getString(errorResId));
    }

    public void setEmailError(String error) {
        email.setError(error);
    }

    public void setEmailError(@StringRes int errorResId) {
        email.setError(getContext().getString(errorResId));
    }

    public void setOldPasswordError(@StringRes int errorResId) {
        password.setError(getContext().getString(errorResId));
    }

    public void setNewPasswordError(@StringRes int errorResId) {
        newPassword.setError(getContext().getString(errorResId));
    }

    public void setConfirmPasswordError(@StringRes int errorResId) {
        confirmNewPassword.setError(getContext().getString(errorResId));
    }

    public void resetPasswordFields() {
        password.setText(null);
        newPassword.setText(null);
        confirmNewPassword.setText(null);
    }
}
