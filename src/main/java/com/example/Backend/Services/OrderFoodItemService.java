package com.example.Backend.Services;

import com.example.Backend.Entity.Order_Food_item;


import com.example.Backend.Repository.OrderFoodItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderFoodItemService {
    @Autowired
    private OrderFoodItemRepository orderFoodItemRepository;
    public OrderFoodItemService() {

    }
    public Order_Food_item saveOrderfood(Order_Food_item o){
        return orderFoodItemRepository.save(o);
    }
    /*public List<Order> getUserOrders(int id){
        List<OrderFoodResult> Orders =orderFoodRepository.findByEmployeeId(id);
        for (:
             ) {
            
        }
    }*/



}
