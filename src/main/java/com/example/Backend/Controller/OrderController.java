package com.example.Backend.Controller;


import com.example.Backend.Entity.Order_Food;
import com.example.Backend.Entity.Orders;
import com.example.Backend.Services.OrderFoodService;
import com.example.Backend.Services.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/orders")
@RestController
public class OrderController {
    private OrderService orderService;
    private OrderFoodService orderFoodService;
    public OrderController(OrderService orderService,OrderFoodService orderFoodService) {
        this.orderService = orderService;
        this.orderFoodService=orderFoodService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/add")
    public Orders AddOrder(@RequestBody Orders order){
        System.out.println();
        orderService.saveOrder(order);
        for (Order_Food orderfood:order.getFoodOrders())
        {
            orderfood.setOrders_id(order.getOrder_id());
            orderFoodService.saveOrderfood(orderfood);
        }

        return order;
    }


    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/userorders")
    public List<Orders> getOrders(@RequestParam int id){
        return orderService.getUserOrders(id);
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderReady")
    public Orders ge(@RequestBody int id){
        return orderService.UpdateOrder(id);
    }
    /*@CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderUser")
    public List<Orders> test(@RequestBody int id){
        return orderService.getUserOrders(id);
    }*/
}
