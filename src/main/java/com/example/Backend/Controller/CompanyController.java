package com.example.Backend.Controller;

import com.example.Backend.Entity.Company;
import com.example.Backend.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/Company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @GetMapping("/all")
    public List<Company> getComapnys(){
        return companyService.getAllCompanys();
    }
    @GetMapping("/Companyname/{CompanyName}")
    public ResponseEntity<Optional<Company>> findCompany(@PathVariable String CompanyName){
        Optional<Company> company=companyService.getACompany(CompanyName);
        if (company.isPresent()){
            return  ResponseEntity.ok(company);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("addCompany")
    public ResponseEntity<?> addCompany(@RequestBody Company company){
        try{

            return  ResponseEntity.ok(companyService.AddCompany(company));
        }catch (Exception e){
            return  new ResponseEntity<>("Error addidng the startupEvent"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("updateCompany")
    public ResponseEntity<?> updateCompany(@RequestBody Company company)
    {
        try {
            Company updatedCompany = companyService.updateCompany(company);
            return ResponseEntity.ok(updatedCompany);
        } catch (NoSuchElementException e) {
            String errorMessage = "Company not found with ID " + company.getCompanyid();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "Error updating the company: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
    @DeleteMapping("DeleteCompany/{company_id}")
    public ResponseEntity<?>DeleteCompany(@PathVariable int  company_id)
    {

        try {
            companyService.deleteCompany(company_id);
            return ResponseEntity.ok("Company deleted successfully");
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting the company: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getCompaniesNames")
    public ResponseEntity<?>GetallCompaniesName()
    {
        try
        {

            return ResponseEntity.ok(companyService.getCompanyName());
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("error in getiing the compnaies names"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
