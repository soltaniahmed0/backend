package com.example.Backend.Services;

import com.example.Backend.Entity.Company;
import com.example.Backend.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    public List<Company> getAllCompanys(){
        return companyRepository.findAll();
    }
    public Optional<Company> getACompany(String companyname){
        return companyRepository.findCompanyByCompanyName(companyname);
    }
    public Company AddCompany(Company company){
        return  companyRepository.save(company);
    }
}
