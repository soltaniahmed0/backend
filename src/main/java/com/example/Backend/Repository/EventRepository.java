package com.example.Backend.Repository;

import com.example.Backend.Entity.StartupEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<StartupEvent,Integer> {

    List<StartupEvent> findByEmployeeId(int employeeId);
}
