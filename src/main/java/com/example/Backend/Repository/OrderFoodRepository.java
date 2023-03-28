package com.example.Backend.Repository;

import com.example.Backend.Entity.Order_Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFoodRepository extends JpaRepository<Order_Food, Integer> {


}
