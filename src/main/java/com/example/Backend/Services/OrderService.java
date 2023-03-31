package com.example.Backend.Services;

import com.example.Backend.Entity.Orders;
import com.example.Backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService {
    List<Orders> Orders;
    @Autowired
    private OrderRepository orderRepository;
    public OrderService() {

        Orders = new ArrayList<Orders>();


    }
    public Orders saveOrder(Orders o){

        return orderRepository.save(o);
    }
    public List<Orders> saveOrders(){
        return orderRepository.saveAll(Orders);
    }

    public List<Orders> getOrders(){
        return  orderRepository.findAll();
    }
    public Orders getOrder(int id){
        return  orderRepository.findById(id).orElse(null);
    }

    public Orders UpdateOrder(int id){
        Orders existingOrder=getOrder(id);
        existingOrder.setReady(true);
        return orderRepository.save(existingOrder);

    }

    
    /*public List<Orders> getUserOrders(int id){
        return orderRepository.findByEmployee_Id(id);
    }*/

}
