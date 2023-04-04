package com.example.Backend.Controller;


import com.example.Backend.Entity.Order_Food;
import com.example.Backend.Entity.Orders;
import com.example.Backend.Services.OrderFoodService;
import com.example.Backend.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
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
        order.setDate(LocalDate.now());
        System.out.println(order.getDate());
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
    public List<Orders> getUserOrders(@RequestParam int id){
        List<Orders> res =orderService.getUserOrders(id);
        System.out.println(res);
        return res;
    }

    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getOrders")
    public List<Orders> getOrders(){
        return orderService.getOrders();
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/orderReady")
    public Orders ge(@RequestBody int id){
        return orderService.UpdateOrder(id);
    }
    /*@CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderUser")
    public List<Orders> test(@RequestBody int id){
        return orderService.getUserOrders(id);
    }*/
}
