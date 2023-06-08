package com.example.Backend.Controller;


import com.example.Backend.Entity.Garniture;
import com.example.Backend.Entity.Order_Food_item;
import com.example.Backend.Entity.FoodsOrders;
import com.example.Backend.Services.GarnitureService;
import com.example.Backend.Services.NotificationService;
import com.example.Backend.Services.OrderFoodItemService;
import com.example.Backend.Services.OrderFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RequestMapping("/Order_food_item")
@RestController
public class OrderController {
    @Autowired
    private NotificationService notificationService;
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

        orderFoodService.saveOrder(order);


        for (Order_Food_item orderfood:order.getFoodOrders())
        {
            orderfood.setOrders_id(order.getOrder_food_id());
            Order_Food_item orderfood1= orderFoodItemService.saveOrderfood(orderfood);
            for (Garniture garniture:orderfood.getGarniture()){
                //garniture.setOrder_food1(orderfood1);

                //AddOrder(garniture);

                Addgar(new Garniture(garniture.getName(),garniture.isChecked(),orderfood1.getOrder_Food_Item_id()));
            }

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
        FoodsOrders res=orderFoodService.UpdateOrder(id);
        notificationService.sendNotification("Order ready", String.valueOf(orderFoodService.getOrder(id).getOrder_food_id()),res.getEmployee().getDeviceToken());
        return res;
    }

    @DeleteMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id){

       orderFoodService.deleteOrder(id);
    }
}
