package com.example.Backend.Controller;


import com.example.Backend.Entity.Garniture;
import com.example.Backend.Entity.Order_Food;
import com.example.Backend.Entity.Orders;
import com.example.Backend.Services.GarnitureService;
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
    private GarnitureService garnitureService;
    public OrderController(OrderService orderService, OrderFoodService orderFoodService, GarnitureService garnitureService) {
        this.orderService = orderService;
        this.orderFoodService=orderFoodService;
        this.garnitureService=garnitureService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/add")
    public Orders AddOrder(@RequestBody Orders order){
        order.setDate(LocalDate.now());
        System.out.println(order.getDate());
        orderService.saveOrder(order);
        System.out.println(order);

        for (Order_Food orderfood:order.getFoodOrders())
        {
            orderfood.setOrders_id(order.getOrder_id());
            Order_Food orderfood1=orderFoodService.saveOrderfood(orderfood);
            for (Garniture garniture:orderfood.getGarniture()){
                //garniture.setOrder_food1(orderfood1);
                System.out.println(orderfood1);
                //AddOrder(garniture);
                System.out.println(garniture.getName());
                Addgar(new Garniture(garniture.getName(),garniture.isChecked(),orderfood1.getOrder_Food_id()));
            }
            System.out.println(orderfood1.toString());
        }
        return order;
    }

    public Garniture Addgar( Garniture garniture) {
        return garnitureService.addgar(garniture);
    }


    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/userorders")
    public List<Orders> getUserOrders(@RequestParam int id){
        return orderService.getUserOrders(id);
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