package com.example.Backend.Repository;

import com.example.Backend.Entity.FoodIngrediant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodIngrediantRepository extends JpaRepository<FoodIngrediant, Integer> {


}
