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
    @OneToMany(mappedBy = "order_food1")
    @JsonIgnoreProperties("order_food1")
    private List<Garniture> garniture;

    @Override
    public String toString() {
        return "Order_Food{" +
                "Order_Food_id=" + Order_Food_id +
                ", orders_id=" + orders_id +
                ", foods=" + foods +
                ", qte=" + qte +

                '}';
    }

    public List<Garniture> getGarniture() {
        if (garniture!=null){
            return garniture;
        }else {
            return new ArrayList<Garniture>();
        }

    }

    public void setGarniture(List<Garniture> garniture) {
        this.garniture = garniture;
    }
}