package com.example.Backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Order_Snack_item {
    @Id
    @GeneratedValue
    private int order_snack_item_id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_snack_id",insertable=false, updatable=false)
    private SnacksOrders snacksorders;
    @Column(name = "order_snack_id")
    private int orders_id;
    @ManyToOne
    @JoinColumn(name = "snack_id")
    private Snack snacks;
    private int  qte;


    @Override
    public String toString() {
        return "Order_Snack{" +
                "Order_Snack_id=" +  order_snack_item_id +
                ", orders_id=" + orders_id +
                ", snacks=" + snacks +
                ", qte=" + qte +

                '}';
    }


}
