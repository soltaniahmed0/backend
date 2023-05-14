package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Room {
    @Id
    @GeneratedValue
    private int room_id;
    private String room_name;
    @Lob
    @Column(name = "img", columnDefinition="longblob")
    private byte[] img;
    private int capacity;
    private String purpose;

}




