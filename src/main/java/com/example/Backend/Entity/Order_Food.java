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
public class Order_Food {
    @Id
    @GeneratedValue
    private int Order_Food_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",insertable=false, updatable=false)
    private Orders orders;
    @Column(name = "order_id")
    private int orders_id;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food foods;
    private int  qte;

    @Override
    public String toString() {
        return "Order_Food{" +
                "Order_Food_id=" + Order_Food_id +
                ", orders_id=" + orders_id +
                ", foods=" + foods +
                ", qte=" + qte +
                '}';
    }
}
