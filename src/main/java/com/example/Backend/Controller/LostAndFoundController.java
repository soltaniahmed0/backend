package com.example.Backend.Controller;


import com.example.Backend.Entity.Category;
import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Services.CategoryService;
import com.example.Backend.Services.LostAndFoundService;
import com.example.Backend.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/lostandfound")
@RestController
public class LostAndFoundController {
    @Autowired
    private NotificationService notificationService;
    private LostAndFoundService lostAndFoundService;
    @Autowired
    public LostAndFoundController(LostAndFoundService lostAndFoundService){
        this.lostAndFoundService=lostAndFoundService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getitem")
    public LostAndFoundItem getItem(@RequestParam int id){
         return lostAndFoundService.getlost(id);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<LostAndFoundItem> getItems(){
        List<LostAndFoundItem> res=lostAndFoundService.getlost();
        System.out.println(res.toString());
        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody LostAndFoundItem lostAndFoundItem){
        lostAndFoundService.addlost(lostAndFoundItem);
        notificationService.sendNotification("lostAndFound",lostAndFoundItem.getTitle());
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody LostAndFoundItem lostAndFoundItem ){

        lostAndFoundService.update(lostAndFoundItem);

    }
//    @CrossOrigin(origins = "http://localhost:59838/")
//    @DeleteMapping("/delete")
//    public void delete(@RequestParam int id){
//        lostAndFoundService.d
//
//    }

}
