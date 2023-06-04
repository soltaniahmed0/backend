package com.example.Backend.Controller;


import com.example.Backend.Entity.Category;
import com.example.Backend.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/cat")
@RestController
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getcat")
    public Category getCat(@RequestParam int id){
         return categoryService.getCat(id);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getcats")

    public List<Category> getcats(){
        List<Category> res=categoryService.getCategorys();

        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Category category){

        categoryService.addcat(category);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Category category ){

        categoryService.update(category);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void deleteFood(@RequestParam int id){
        categoryService.deleteCat(id);

    }

}
