package com.example.Backend.Controller;

import com.example.Backend.Entity.Order_Snack_item;
import com.example.Backend.Entity.SnacksOrders;
import com.example.Backend.Services.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/snacksorders")
@RestController
public class SnacksOrderController {
    private OrderSnackService orderSnackService;
    private OrderSnackItemService orderSnackItemService ;

    public SnacksOrderController(OrderSnackService orderSnackService,OrderSnackItemService orderSnackItemService) {
        this.orderSnackItemService=orderSnackItemService;
        this.orderSnackService =      orderSnackService  ;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @PostMapping("/add")
    public SnacksOrders AddOrder(@RequestBody SnacksOrders order){
        order.setDate(LocalDate.now());
        orderSnackService.saveOrder(order);
        for (Order_Snack_item ordersnack:order.getSnacksOrders())
        {
            ordersnack.setOrders_id(order.getOrder_snack_id());
            Order_Snack_item ordersnackS= orderSnackItemService.saveOrdersnackItem(ordersnack);
            System.out.println(ordersnackS);

        }
        return order;
    }




    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/userorders")
    public List<SnacksOrders> getUserOrders(@RequestParam int id){
        return orderSnackService.getUserSnacksOrders(id);
    }

    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getOrders")
    public List<SnacksOrders> getOrders(){
        return orderSnackService.getSnacksOrders();
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/orderReady")
    public SnacksOrders ge(@RequestBody int id){
        return orderSnackService.UpdateOrder(id);
    }
    /*@CrossOrigin(origins = "http://localhost:57384")
    @PutMapping("/orderUser")
    public List<Orders> test(@RequestBody int id){
        return orderService.getUserOrders(id);
    }*/
}
