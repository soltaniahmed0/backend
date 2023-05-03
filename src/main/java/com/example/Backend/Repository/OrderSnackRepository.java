package com.example.Backend.Repository;

import com.example.Backend.Entity.SnacksOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderSnackRepository extends JpaRepository<SnacksOrders, Integer> {

    //List<Orders> findByReadyAndUser_id(Boolean ready,int user_id);


    /*@Query("SELECT o FROM Orders o WHERE o.employee.employee_id = :userId")*/
    List<SnacksOrders> findByEmployee_Id(int id);
    List<SnacksOrders> findByReady(boolean r);
    List<SnacksOrders> findByEmployee_IdAndReady(int id,boolean r);

}
