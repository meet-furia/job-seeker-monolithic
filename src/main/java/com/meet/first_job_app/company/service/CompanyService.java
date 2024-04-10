package com.meet.first_job_app.company.service;


import com.meet.first_job_app.company.entities.Company;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(Long id);
    List<Company> getAllCompanies();

    Company createCompany(Company company);
    List<Company> createAllCompanies(List<Company> companies);

    Company updateCompany(Company updatedCompany, Long id);
    List<Company> updateAllCompanies(List<Company> companies);

    Boolean deleteCompanyById(Long id);
    Boolean deleteAllCompanies();
}
