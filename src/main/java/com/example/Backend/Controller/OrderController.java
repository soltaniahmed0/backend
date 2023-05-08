package com.example.Backend.Controller;


import com.example.Backend.Entity.Garniture;
import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Services.GarnitureService;
import com.example.Backend.Services.OrderFoodItemService;
import com.example.Backend.Services.OrderFoodService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RequestMapping("/Order_food_item")
@RestController
public class OrderController {
    private OrderFoodService orderFoodService;
    private OrderFoodItemService orderFoodItemService;
    private GarnitureService garnitureService;
    public OrderController(OrderFoodService orderFoodService, OrderFoodItemService orderFoodItemService, GarnitureService garnitureService) {
        this.orderFoodService = orderFoodService;
        this.orderFoodItemService = orderFoodItemService;
        this.garnitureService=garnitureService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/add")
    public FoodsOrders AddOrder(@RequestBody FoodsOrders order){
        order.setDate(LocalDate.now());
        System.out.println(order.getDate());
        orderFoodService.saveOrder(order);
        System.out.println(order);

        for (Order_Food_item orderfood:order.getFoodOrders())
        {
            orderfood.setOrders_id(order.getOrder_food_id());
            Order_Food_item orderfood1= orderFoodItemService.saveOrderfood(orderfood);
            for (Garniture garniture:orderfood.getGarniture()){
                //garniture.setOrder_food1(orderfood1);
                System.out.println(orderfood1);
                //AddOrder(garniture);
                System.out.println(garniture.getName());
                Addgar(new Garniture(garniture.getName(),garniture.isChecked(),orderfood1.getOrder_Food_Item_id()));
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
    public List<FoodsOrders> getUserOrders(@RequestParam int id){
        return orderFoodService.getUserOrders(id);
    }

    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getOrders")
    public List<FoodsOrders> getOrders(){
        return orderFoodService.getOrders();
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderReady/{id}")
    public FoodsOrders setOrderReady(@PathVariable int id){
        return orderFoodService.UpdateOrder(id);
    }
    /*@CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderUser")
    public List<Orders> test(@RequestBody int id){
        return orderService.getUserOrders(id);
    }*/
    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id){
       orderFoodService.deleteOrder(id);
    }
}
