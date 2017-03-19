package com.romao.demo.model;

import com.j256.ormlite.support.ConnectionSource;
import com.romao.demo.model.entities.Company;
import com.romao.demo.network.entities.CompanyResponse;
import com.romao.demo.utils.ModelFromResponseUtils;

import java.sql.SQLException;


public class CompanyDao extends BaseDao<Company, Integer> {

    public CompanyDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Company.class);
    }

    public void saveCompanyOrm(CompanyResponse companyResponse) {
        try {
            deleteById(companyResponse.id);
            Company company = ModelFromResponseUtils.companyFromResponse(companyResponse);
            createOrUpdate(company);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Company getById(int companyId) {
        try {
            return queryForId(companyId);
        } catch (SQLException e) {
            e.printStackTrace();
            return new Company();
        }
    }


}
