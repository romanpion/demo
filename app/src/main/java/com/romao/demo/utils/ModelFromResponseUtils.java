package com.romao.demo.utils;

import com.romao.demo.model.entities.Company;
import com.romao.demo.model.entities.Location;
import com.romao.demo.model.entities.User;
import com.romao.demo.network.entities.CompanyResponse;
import com.romao.demo.network.entities.LocationResponse;
import com.romao.demo.network.entities.UserResponse;

public class ModelFromResponseUtils {

    public static Location locationFromResponse(LocationResponse locationRes) {
        Location location = new Location();
        if (locationRes != null) {
            location.setId(locationRes.id);
            location.setName(locationRes.name);
        }
        return location;
    }

    public static Company companyFromResponse(CompanyResponse companyRes) {
        Company company = new Company();
        if (companyRes != null) {
            company.setId(companyRes.id);
            company.setLocation(locationFromResponse(companyRes.location));
            company.setName(companyRes.name);
            company.setFounded(companyRes.founded);
            company.setImageUrl(companyRes.imageUrl);
            company.setIndustry(companyRes.industry);
            company.setPaymentVerified(companyRes.isPaymentVerified);
            company.setRaiting(companyRes.rating);
            company.setDescription(companyRes.description);
            company.setCin(companyRes.cin);
        }
        return company;
    }

    public static User userFromResponse(UserResponse userResponse) {
        User user = new User();
        if (userResponse != null) {
            user.setId(userResponse.id);
            user.setUserId(userResponse.id);
            user.setFirstName(userResponse.firstName);
            user.setLastName(userResponse.lastName);
            user.setMiddleInitial(userResponse.middleInitial);
            user.setEmail(userResponse.email);
            user.setCompany(companyFromResponse(userResponse.companyResponse));
            user.setCity(userResponse.city);
            user.setCountry(userResponse.country);
            user.setCompanyId(userResponse.companyId);
            user.setEmployeeNumber(userResponse.employeeNumber);
            user.setJobTitle(userResponse.jobTitle);
            user.setLinkedInProfile(userResponse.linkedInProfile);
            user.setOfficeLocation(userResponse.officeLocation);
            user.setPhoneNumber(userResponse.phoneNumber);
            user.setResetPasswordToken(userResponse.resetPasswordToken);
            user.setUserRoles(userResponse.roles);
        }
        return user;
    }

}