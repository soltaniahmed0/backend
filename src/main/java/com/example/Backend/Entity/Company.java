package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue
    private int company_id;
    @Column(unique = true)
    private String companyName;
    @Lob
    @Column(name = "company_logo", columnDefinition="longblob")
    private byte[] company_logo;


    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public byte[] getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(byte[] company_logo) {
        this.company_logo = company_logo;
    }



}
