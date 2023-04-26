package com.example.Backend.Services;

import com.example.Backend.Entity.Order_Food;


import com.example.Backend.Repository.OrderFoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderFoodService {
    @Autowired
    private OrderFoodRepository orderFoodRepository;
    public OrderFoodService() {

    }
    public Order_Food saveOrderfood(Order_Food o){
        return orderFoodRepository.save(o);
    }
    /*public List<Order> getUserOrders(int id){
        List<OrderFoodResult> Orders =orderFoodRepository.findByEmployeeId(id);
        for (:
             ) {
            
        }
    }*/



}