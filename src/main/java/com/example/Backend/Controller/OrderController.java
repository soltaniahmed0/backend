package com.example.Backend.Controller;


import com.example.Backend.Entity.Orders;
import com.example.Backend.Services.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/cart")
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/add")
    public Orders AddOrder(@RequestBody Orders order){
        orderService.saveOrder(order);
        return order;
    }


    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/orders")
    public List<Orders> getOrders(){
        return orderService.getOrders();
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
