
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
public class Garniture {
    @Id
    @GeneratedValue
    private int garnitureid;
    private String name;
    private boolean checked;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Order_Food_id",insertable=false, updatable=false)
    private Order_Food_item order_food1;
    @Column(name = "Order_Food_id",nullable = false)
    private int Order_Food_id;

    public Garniture(String name, boolean checked, int order_Food_id) {
        this.name = name;
        this.checked = checked;
        this.Order_Food_id = order_Food_id;
    }
}

