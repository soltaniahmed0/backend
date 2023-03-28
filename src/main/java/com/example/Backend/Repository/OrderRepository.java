package com.example.Backend.Repository;

import com.example.Backend.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

    //List<Orders> findByReadyAndUser_id(Boolean ready,int user_id);


    @Query("SELECT o FROM Orders o WHERE o.user.user_id = :userId")
    List<Orders> findByUser_id(@Param("userId") int userId);
}
