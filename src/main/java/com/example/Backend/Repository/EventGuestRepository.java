package com.example.Backend.Repository;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.EventGuest;
import com.example.Backend.Entity.StartupEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventGuestRepository  extends JpaRepository<EventGuest,Integer> {
    Optional<EventGuest> findByEventAndEmployee(StartupEvent event, Employee employee);
    @Modifying
    @Query("UPDATE EventGuest e SET e.interested = :interested, e.going = :going WHERE e.id = :id")
    void updateEventGuestStatus(@Param("id") Integer id, @Param("interested") boolean interested, @Param("going") boolean going);
}
