package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Appointment {
    @Id
    @GeneratedValue
    int id;
    Timestamp startTime;
    Timestamp endTime;
    String subject;
    String notes;
    boolean approve;
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
}
