package com.romao.demo.network.entities;

import com.romao.demo.model.entities.Company;

import java.util.List;

public class UserUpdateRequest {

    public int UserId;

    public int id;

    public String email;

    public String phoneNumber;

    public Integer employeeNumber;

    public String jobTitle;

    public String officeLocation;

    public String firstName;

    public String lastName;

    public String middleInitial;

    public String linkedinProfile;

    public List<DepartmentRequest> departments;


    public static class DepartmentRequest {
        public int companyId;

        public Company company;

        public String name;

        public Integer id;
    }
}
