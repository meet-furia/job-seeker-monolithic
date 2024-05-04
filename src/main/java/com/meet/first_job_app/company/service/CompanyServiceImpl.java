package com.meet.first_job_app.company.service;

import com.meet.first_job_app.company.entities.Company;
import com.meet.first_job_app.company.repository.CompanyRepository;
import com.meet.first_job_app.job.entities.Job;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> createAllCompanies(List<Company> companies) {
        return companyRepository.saveAll(companies);
    }

    @Override
    public Company updateCompany(Company updatedCompany, Long id) {
        Company existingCompany = getCompanyById(id);
        if (existingCompany == null) {
            return null;
        }

        existingCompany.setName(updatedCompany.getName());
        existingCompany.setDescription(updatedCompany.getDescription());
        existingCompany.setJobs(updatedCompany.getJobs());
        existingCompany.setReviews(updatedCompany.getReviews());
        companyRepository.save(existingCompany);
        return existingCompany;
    }

    @Override
    public List<Company> updateAllCompanies(List<Company> companies) {
        List<Company> updatedCompanies = new ArrayList<>();
        for (Company company : companies) {
            Company companyUpdated = updateCompany(company, company.getId());
            if (companyUpdated != null) {
                updatedCompanies.add(companyUpdated);
            }
        }
        return updatedCompanies;
    }

    @Override
    public Boolean deleteCompanyById(Long id) {
        Boolean companyExists = companyRepository.existsById(id);
        if (companyExists) {
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override

    public Boolean deleteAllCompanies() {
        if (getAllCompanies().isEmpty()) {
            return false;
        }
        companyRepository.deleteAll();
        return true;
    }
}
