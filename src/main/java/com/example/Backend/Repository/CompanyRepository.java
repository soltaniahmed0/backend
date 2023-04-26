package com.example.Backend.Repository;

import com.example.Backend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
   Optional<Company>findCompanyByCompanyName(String CompanyName);
}
