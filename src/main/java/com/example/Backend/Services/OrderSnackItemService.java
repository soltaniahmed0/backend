package com.example.Backend.Services;

import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Entity.Order_Snack_item;
import com.example.Backend.Repository.OrderFoodItemRepository;
import com.example.Backend.Repository.OrderSnackItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderSnackItemService {
    @Autowired
    private OrderSnackItemRepository orderSnackItemRepository;
    public OrderSnackItemService() {

    }
    public Order_Snack_item saveOrdersnackItem(Order_Snack_item o){
        return orderSnackItemRepository.save(o);
    }




}
