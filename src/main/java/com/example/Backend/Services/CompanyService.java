package com.example.Backend.Services;

import com.example.Backend.Entity.Company;
import com.example.Backend.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    public Company updateCompany(Company company) {
        Optional<Company> existingCompany = companyRepository.findById(company.getCompanyid());
        if (existingCompany.isPresent()) {
            Company updatedCompany = existingCompany.get();
            updatedCompany.setCompanyName(company.getCompanyName());
            updatedCompany.setCompanylogo(company.getCompanylogo());
            return companyRepository.save(updatedCompany);
        } else {
            throw new NoSuchElementException("Company not found with ID " + company.getCompanyid());
        }
    }
    public void deleteCompany(int  companyId) {
        Optional<Company> existingCompany = companyRepository.findById(companyId);
        if (existingCompany.isPresent()){
            companyRepository.deleteById(companyId);
        }
        else {
            throw new NoSuchElementException("can't delete company  with ID " + companyId);
        }
    }
    public List<String> getCompanyName(){
        return companyRepository.findNames();
    }
}
