package com.example.Backend.Services;

import com.example.Backend.Entity.Order_Food;

import com.example.Backend.Repository.OrderFoodRepository;

import com.example.Backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFoodService {
    @Autowired
    private OrderFoodRepository orderFoodRepository;
    public OrderFoodService() {

    }
    public Order_Food saveOrderfood(Order_Food o){
        return orderFoodRepository.save(o);
    }




}
