package com.example.Backend.Repository;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.EventGuest;
import com.example.Backend.Entity.StartupEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventGuestRepository  extends JpaRepository<EventGuest,Integer> {
    Optional<EventGuest> findByEventAndEmployee(StartupEvent event, Employee employee);
}
