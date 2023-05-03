package com.example.Backend.Repository;

import com.example.Backend.Entity.FoodsOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<FoodsOrders, Integer> {

    //List<Orders> findByReadyAndUser_id(Boolean ready,int user_id);


    /*@Query("SELECT o FROM Orders o WHERE o.employee.employee_id = :userId")*/
    List<FoodsOrders> findByEmployee_Id(int id);
    List<FoodsOrders> findByReady(boolean r);
    List<FoodsOrders> findByEmployee_IdAndReady(int id, boolean r);

}
