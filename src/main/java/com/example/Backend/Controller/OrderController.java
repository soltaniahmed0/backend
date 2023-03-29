package com.example.Backend.Controller;


import com.example.Backend.Entity.Orders;
import com.example.Backend.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/order")
@RestController
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/addOrder")
    public Orders AddOrder(@RequestBody Orders order){
        //orderService.saveOrders();
        return orderService.saveOrder(order);
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
    @CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderUser")
    public List<Orders> test(@RequestBody int id){
        return orderService.getUserOrders(id);
    }
}
