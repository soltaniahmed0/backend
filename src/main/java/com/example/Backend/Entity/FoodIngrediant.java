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
public class FoodIngrediant {
    @Id
    @GeneratedValue
    private int FoodIngrediant_id;
    @Column(name = "foodID")
    private int foodID;
    @Column(name = "ingrediant_id")
    private int ingrediant_id;


}
