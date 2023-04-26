package com.example.Backend.Controller;

import com.example.Backend.Entity.Company;
import com.example.Backend.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping("/all")
    public List<Company> getComapnys(){
        return companyService.getAllCompanys();
    }
    @GetMapping("/Company/{CompanyName}")
    public ResponseEntity<Optional<Company>> findCompany(@PathVariable String CompanyName){
        Optional<Company> company=companyService.getACompany(CompanyName);
        if (company.isPresent()){
            return  ResponseEntity.ok(company);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}
