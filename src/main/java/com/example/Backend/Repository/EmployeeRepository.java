package com.example.Backend.Repository;

import com.example.Backend.Entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByEmail(String email);
    List<Employee> findAll();
    Employee findByPosition(String position);
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.photo = :photo WHERE e.employee_id = :id")
    void savePhoto(@Param("id") Integer id, @Param("photo") byte[] photo);
    @Modifying
    @Query("UPDATE Employee SET password = :password WHERE employee_id = :id")
    void changePassword(@Param("id")Integer id,@Param("password")String password);

}
