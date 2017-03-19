package com.romao.demo.model.entities;

import android.provider.BaseColumns;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.romao.demo.model.SerializedList;
import com.romao.demo.network.entities.UserRoleResponse;

import java.util.List;

@DatabaseTable(tableName = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "users";

    public static final String ID_COLUMN = BaseColumns._ID;
    public static final String LOGIN_COLUMN = "login";
    public static final String FIRST_NAME_COLUMN = "firstName";
    public static final String LAST_NAME_COLUMN = "lastName";
    public static final String MIDDLE_INITIAL_COLUMN = "middleInitial";
    public static final String EMAIL_COLUMN = "email";
    public static final String COMPANY_COLUMN = "company";
    public static final String COMPANY_ID_COLUMN = "company_id";

    public static final String USER_ROLES_COLUMN = "user_roles";
    public static final String USER_ID_COLUMN = "userId";
    public static final String LINKEDIN_PROFILE_COLUMN = "linkedinProfile";
    public static final String RESET_PASSWORD_TOKEN_COLUMN = "resetPasswordToken";
    public static final String CITY_COLUMN = "city";
    public static final String JOB_TITLE_COLUMN = "jobTItle";
    public static final String OFFICE_LOCATION_COLUMN = "officeLocation";
    public static final String COUNTRY_COLUMN = "country";
    public static final String EMPLOYEE_NUMBER_COLUMN = "employeeNumber";
    public static final String PHONE_NUMBER_COLUMN = "phoneNumber";
    public static final String CV_COLUMN = "cv";

    @DatabaseField(id = true, columnName = ID_COLUMN)
    private int id;

    @DatabaseField(columnName = LOGIN_COLUMN)
    private String login;

    @DatabaseField(columnName = FIRST_NAME_COLUMN)
    private String firstName;

    @DatabaseField(columnName = LAST_NAME_COLUMN)
    private String lastName;

    @DatabaseField(columnName = MIDDLE_INITIAL_COLUMN)
    private String middleInitial;

    @DatabaseField(columnName = EMAIL_COLUMN)
    private String email;

    @DatabaseField(columnName = COMPANY_ID_COLUMN)
    private int companyId;

    @DatabaseField(columnName = USER_ROLES_COLUMN, dataType = DataType.SERIALIZABLE)
    private SerializedList<UserRoleResponse> userRoles;

    @DatabaseField(columnName = USER_ID_COLUMN)
    private int userId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = COMPANY_COLUMN)
    private Company company;

    @DatabaseField(columnName = LINKEDIN_PROFILE_COLUMN)
    private String linkedInProfile;

    @DatabaseField(columnName = RESET_PASSWORD_TOKEN_COLUMN)
    private String resetPasswordToken;

    @DatabaseField(columnName = CITY_COLUMN)
    private String city;

    @DatabaseField(columnName = COUNTRY_COLUMN)
    private String country;

    @DatabaseField(columnName = OFFICE_LOCATION_COLUMN)
    private String officeLocation;

    @DatabaseField(columnName = JOB_TITLE_COLUMN)
    private String jobTitle;

    @DatabaseField(columnName = PHONE_NUMBER_COLUMN)
    private String phoneNumber;

    @DatabaseField(columnName = EMPLOYEE_NUMBER_COLUMN)
    private int employeeNumber;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public List<UserRoleResponse> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(SerializedList<UserRoleResponse> userRoles) {
        this.userRoles = userRoles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getLinkedInProfile() {
        return linkedInProfile;
    }

    public void setLinkedInProfile(String linkedInProfile) {
        this.linkedInProfile = linkedInProfile;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }
}
