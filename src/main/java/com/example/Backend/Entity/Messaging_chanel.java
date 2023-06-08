package com.example.Backend.Entity;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.MessageData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Messaging_chanel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long channel_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
    private Employee user;

    @ManyToOne
    @JoinColumn(name = "user1_id", referencedColumnName = "employee_id")
    private Employee user1;

    int unreadMessageCount;


}

