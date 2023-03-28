package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EmpOrder")
public class EmpOrder {
    @Id
    @GeneratedValue
    private Integer order_id;
    private String order_date;
    private Boolean ready;


}
