package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Snack {
    @Id
    @GeneratedValue
    private int snack_id;
    private String snackName;
    @Column(name = "img", columnDefinition="longblob")
    private byte[] img;
    private double price;
    private int qtt;
    private String description;

}


