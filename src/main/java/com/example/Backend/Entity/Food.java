package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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
    @Lob
    @Column(name = "img", columnDefinition="longblob")
    private byte[] img;
    public float price;
    public boolean available;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    Category cat;
    public String description;




    public Food(String foodName, byte[] img, float price, Category cat, boolean available, String description) {
        this.foodName = foodName;
        this.img = img;
        this.price = price;
        this.cat=cat;
        this.description = description;
        this.available=available;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodID=" + foodID +
                ", foodName='" + foodName + '\'' +

                ", price=" + price +
                ", available=" + available +
                ", cat=" + cat +
                ", description='" + description + '\'' +
                '}';
    }
}