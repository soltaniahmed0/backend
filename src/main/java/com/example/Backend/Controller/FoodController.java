package com.example.Backend.Controller;



import com.example.Backend.Entity.Food;

import com.example.Backend.Services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/food")
@RestController
public class FoodController {

    private FoodService foodService;


    @Autowired
    public FoodController(FoodService foodService){
        this.foodService=foodService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getfood")
    public Food getFood(@RequestParam int id){

         return foodService.getFood(id);

    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getfoods")

    public List<Food> getFood(){
        return foodService.getFoodList();
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Food FoodStrings){

        foodService.addFood(FoodStrings);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Food foodStrings){
        foodService.update(foodStrings);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public String deleteFood(@RequestParam int id){
        foodService.deleteFood(id);
        return "aaaaaaaa";
    }

}
