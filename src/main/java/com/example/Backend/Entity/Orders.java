package com.example.Backend.Entity;

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
public class Orders {
    @Id
    @GeneratedValue
    private int order_id;
    private String order_date;
    private boolean ready;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee employee;
    @OneToMany(mappedBy = "orders")
    private List<Order_Food> foodOrders;

}
