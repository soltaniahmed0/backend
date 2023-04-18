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
public class LostAndFoundItem {
    @Id
    @GeneratedValue
    private int item_id;
    private String title;
    private String description;
    private String location;
    @ManyToOne
    @JoinColumn(name = "finder_id")
    private Employee finder;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Employee owner;
    @Lob
    @Column(name = "img", columnDefinition="longblob")
    private byte[] img;

}
