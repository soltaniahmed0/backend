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
    private int companyid;
    @Column(unique = true)
    private String companyName;
    @Lob
    @Column(name = "company_logo", columnDefinition="longblob")
    private byte[] companylogo;



    public String getCompanyName() {
        return companyName;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public byte[] getCompanylogo() {
        return companylogo;
    }

    public void setCompanylogo(byte[] companylogo) {
        this.companylogo = companylogo;
    }
}
