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
    private int snackId;
    private String snackName;
    private String img;
    private double price;
    private int qtt;
    private String description;
}


