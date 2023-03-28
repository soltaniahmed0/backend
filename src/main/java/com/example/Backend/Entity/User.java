package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    private int user_id;
    private String name, lastname,email,password,entreprise,position,tel;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;


}
