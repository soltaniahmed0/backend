package com.example.Backend.Repository;

import com.example.Backend.Entity.Order_Food_item;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderFoodItemRepository extends JpaRepository<Order_Food_item, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Order_Food_item o WHERE o.orders_id = :orders_id")
    void deleteByOrdersId(@Param("orders_id") int orders_id);
/*
    @Query(value = "SELECT DISTINCT orders.order_id, orders.order_date, orders.ready, orders.employee_id, order_food.food_id, order_food.qte FROM orders JOIN order_food ON orders.order_id = order_food.order_id WHERE orders.employee_id = ?1", nativeQuery = true)
    List<OrderFoodResult> findByEmployeeId(int employeeId);*/
}
