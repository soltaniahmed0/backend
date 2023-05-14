package com.example.Backend.Repository;

import com.example.Backend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
   Optional<Company>findCompanyByCompanyName(String CompanyName);
   Company findByCompanyName(String companyname);
   @Query("SELECT companyName FROM Company")
   List<String> findNames();
   Company findByCompanyid(int id);

}
