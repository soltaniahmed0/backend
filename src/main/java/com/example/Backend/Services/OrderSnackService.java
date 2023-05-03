package com.example.Backend.Services;

import com.example.Backend.Entity.SnacksOrders;
import com.example.Backend.Repository.OrderSnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderSnackService {

    @Autowired
    private OrderSnackRepository snacksRepository;
    public OrderSnackService() {

    }
    public SnacksOrders saveOrder(SnacksOrders o){
        SnacksOrders res =snacksRepository.save(o);
        System.out.println(res);
        return res;
    }


    public List<SnacksOrders> getSnacksOrders(){
        return  snacksRepository.findAll();
    }
    public SnacksOrders getOrder(int id){
        return  snacksRepository.findById(id).orElse(null);
    }

    public SnacksOrders UpdateOrder(int id){
        SnacksOrders existingOrder=getOrder(id);
        existingOrder.setReady(true);
        return snacksRepository.save(existingOrder);

    }

    public List<SnacksOrders> getUserSnacksOrders(int i){
        return snacksRepository.findByEmployee_Id(i);
    }
    public List<SnacksOrders> getSnacksOrders(int i){
        return snacksRepository.findAll();
    }
    public List<SnacksOrders> getnotReadySnacksOrders(){
        return snacksRepository.findByReady(false);
    }
    public List<SnacksOrders> getnotReadyUserSnacksOrders(int i){
        return snacksRepository.findByEmployee_IdAndReady(i,false);
    }


}
