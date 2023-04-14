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
public class Food {
    @Id
    @GeneratedValue
    public int foodID;
    public String foodName;

    public float price;
    public boolean available;
    @Lob
    @Column(name = "photo", columnDefinition="longblob")
    private byte[] img;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    Category cat;




    public Food(String foodName, byte[] img, float price,Category cat, boolean available) {
        this.foodName = foodName;
        this.img = img;
        this.price = price;
        this.cat=cat;
        this.available = available;



    }



}
