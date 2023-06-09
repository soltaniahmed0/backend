package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "StartupEvent")

public class StartupEvent {
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
    @Column(name = "Eventimg", columnDefinition="longblob")
    private byte[] eventimg;
    private String eventStartTime;
    private String eventEndTime;
    private String location;
    private String host;
    private double price;
}
