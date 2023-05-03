package com.example.Backend.Services;

import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderFoodService {
    List<FoodsOrders> FoodsOrders;
    @Autowired
    private OrderRepository orderRepository;
    public OrderFoodService() {

        FoodsOrders = new ArrayList<FoodsOrders>();


    }
    public FoodsOrders saveOrder(FoodsOrders o){
        FoodsOrders res =orderRepository.save(o);
        System.out.println(res);
        return res;
    }
    public List<FoodsOrders> saveOrders(){
        return orderRepository.saveAll(FoodsOrders);
    }

    public List<FoodsOrders> getOrders(){
        return  orderRepository.findAll();
    }
    public FoodsOrders getOrder(int id){
        return  orderRepository.findById(id).orElse(null);
    }

    public FoodsOrders UpdateOrder(int id){
        FoodsOrders existingOrder=getOrder(id);
        existingOrder.setReady(true);
        return orderRepository.save(existingOrder);

    }

    public List<FoodsOrders> getUserOrders(int i){
        return orderRepository.findByEmployee_Id(i);
    }
    public List<FoodsOrders> getOrders(int i){
        return orderRepository.findAll();
    }
    public List<FoodsOrders> getnotReadyOrders(){
        return orderRepository.findByReady(false);
    }
    public List<FoodsOrders> getnotReadyUserOrders(int i){
        return orderRepository.findByEmployee_IdAndReady(i,false);
    }


}
