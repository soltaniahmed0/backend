package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Events")

public class Events {
    @Id
    @GeneratedValue
    private int eventId;
    private String eventName;
    private String eventDescription;
    @Temporal(TemporalType.DATE)
    private Date eventDate;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private boolean approve;

}
