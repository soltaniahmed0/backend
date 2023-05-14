package com.example.Backend.Repository;


import com.example.Backend.Entity.Appointment;
import com.example.Backend.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


}
