package com.meet.first_job_app.company.controller;

import com.meet.first_job_app.company.entities.Company;
import com.meet.first_job_app.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(companies, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        Company savedCompany = companyService.createCompany(company);
        if (savedCompany != null) {
            return new ResponseEntity<>(savedCompany, HttpStatus.OK);
        } else {
            String errorMessage = "Failed to create company. Please provide valid data.";
            return new ResponseEntity<>("errorMessage", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<?> createCompanies(@RequestBody List<Company> companies) {
        List<Company> savedCompanies = companyService.createAllCompanies(companies);
        if (savedCompanies != null) {
            return new ResponseEntity<>(savedCompanies, HttpStatus.OK);
        } else {
            String errorMessage = "Failed to create company. Please provide valid data.";
            return new ResponseEntity<>("errorMessage", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompanybyId(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Company updatedEntity = companyService.updateCompany(updatedCompany, id);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/all")
    public ResponseEntity<?> updateCompanies(@RequestBody List<Company> companies) {
        List<Company> updatedCompanies = companyService.updateAllCompanies(companies);
        if (!updatedCompanies.isEmpty()) {
            return new ResponseEntity<>(updatedCompanies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Companies found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return new ResponseEntity<>("Id: " + id + " Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Company found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/all")
    public ResponseEntity <String> deleteAllCompanies() {
        boolean deleted = companyService.deleteAllCompanies();
        if (deleted) {
            return new ResponseEntity<>("Companies Deleted Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Companies present to be deleted", HttpStatus.NOT_FOUND);
        }
    }

}
