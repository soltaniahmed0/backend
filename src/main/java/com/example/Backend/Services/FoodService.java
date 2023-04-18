package com.example.Backend.Services;


import com.example.Backend.Entity.Food;

import com.example.Backend.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    private List<Food> foodList;
    @Autowired
    private FoodRepository foodRepository;
    public FoodService() {
/*
        foodList = new ArrayList<>();
        foodList.addAll(Arrays.asList(
                new Food(),
                new Food( "Coca-Cola", "https://www.thestreet.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTkxNTY4NjI2MDE5NDc2OTc4/coca-cola-clouds.jpg", 10, Food.Category.drink, true),
                new Food("Pizza Neptune", "http://www.tunisiadeal.com/deals/19/12/d5604-vFx2ku6J/i1.jpg", 10, Food.Category.pizza, true),
                new Food( "SEAFOOD SOUP", "http://bevcooks.com/wp-content/uploads/2020/04/12A24A1F-0E73-499C-8337-EECEBCDB542D-scaled.jpg", 10, Food.Category.soup, true),
                new Food( "ham sandwich", "https://media.istockphoto.com/id/504585434/photo/sub-deli-sandwich-baguette-with-ham-isolated.jpg?s=612x612&w=0&k=20&c=VxUcDzrr6uXdjua_zRSUwMe8MpPkD7mF6zODFNgF7dE=", 10, Food.Category.sandwich, true),
                new Food( "chocolate-covered-oreos", "https://hips.hearstapps.com/hmg-prod/images/chocolate-covered-oreos-vertical-1547769854.jpg?resize=480:*", 10, Food.Category.desert, true)));
*/
    }
    public List<Food> getFoodList() {
        return foodRepository.findAll();
    }

    public void addFood(Food f ){
        Food food=new Food(f.getFoodName(),f.getImg(),f.getPrice(),f.getCat(),f.available,f.description);


        foodRepository.save(food);
        //foodList.add(new Food(n.getFoodID(),n.getFoodName(),n.getImg(),n.getPrice(),_category(n.getCat()),n.available));
    }
    public void deleteFood(int id){
        foodRepository.deleteById(id);
    }
    public Food getFood(int id) {
        return  foodRepository.findById(id).orElse(null);
    }

    public Food update(Food foodStrings) {
        //Food foodForm=new Food(foodStrings.getFoodID(),foodStrings.getFoodName(),foodStrings.getImg(),foodStrings.getPrice(),_category(foodStrings.getCat()),foodStrings.isAvailable());
        Food existingOrder=getFood(foodStrings.getFoodID());
        existingOrder=foodStrings;

        return foodRepository.save(existingOrder);


    }


}