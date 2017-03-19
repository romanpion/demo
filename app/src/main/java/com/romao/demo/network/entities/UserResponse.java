package com.romao.demo.network.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.romao.demo.model.SerializedList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    @JsonProperty("Id")
    public int id;

    @JsonProperty("FirstName")
    public String firstName;

    @JsonProperty("LastName")
    public String lastName;

    @JsonProperty("MiddleInitial")
    public String middleInitial;

    @JsonProperty("Email")
    public String email;

    @JsonProperty("UserName")
    public String userName;

    @JsonProperty("Roles")
    public SerializedList<UserRoleResponse> roles;

    @JsonProperty("CompanyId")
    public int companyId;

    @JsonProperty("Company")
    public CompanyResponse companyResponse;

    @JsonProperty("LinkedInProfile")
    public String linkedInProfile;

    @JsonProperty("ResetPasswordToken")
    public String resetPasswordToken;

    @JsonProperty("City")
    public String city;

    @JsonProperty("Country")
    public String country;

    @JsonProperty("OfficeLocation")
    public String officeLocation;

    @JsonProperty("JobTitle")
    public String jobTitle;

    @JsonProperty("PhoneNumber")
    public String phoneNumber;

    @JsonProperty("EmployeeNumber")
    public int employeeNumber;
}
