package com.example.Backend.Repository;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.EventGuest;
import com.example.Backend.Entity.StartupEvent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventGuestRepository  extends JpaRepository<EventGuest,Integer> {
    Optional<EventGuest> findByEventAndEmployee(StartupEvent event, Employee employee);
    @Modifying
    @Query("UPDATE EventGuest e SET e.interested = :interested, e.going = :going WHERE e.id = :id")
    void updateEventGuestStatus(@Param("id") Integer id, @Param("interested") boolean interested, @Param("going") boolean going);
    @Modifying
    @Query("SELECT e.employee FROM EventGuest e WHERE e.id = :id")
    List<Employee> getGuest(@Param("id") Integer id);

    @Query("SELECT eg.employee FROM EventGuest eg WHERE eg.event = :event AND (eg.interested = true OR eg.going = true)")
    List<Employee> findEmployeesByEventAndInterestedOrGoing(@Param("event") StartupEvent event);

    @Transactional
    void deleteByEvent(StartupEvent event);
}
