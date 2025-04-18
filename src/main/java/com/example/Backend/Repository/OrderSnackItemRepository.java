package com.example.Backend.Repository;

import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Entity.Order_Snack_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSnackItemRepository extends JpaRepository<Order_Snack_item, Integer> {
/*
    @Query(value = "SELECT DISTINCT orders.order_id, orders.order_date, orders.ready, orders.employee_id, order_food.food_id, order_food.qte FROM orders JOIN order_food ON orders.order_id = order_food.order_id WHERE orders.employee_id = ?1", nativeQuery = true)
    List<OrderFoodResult> findByEmployeeId(int employeeId);*/
}
