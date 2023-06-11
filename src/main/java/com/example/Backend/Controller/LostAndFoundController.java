package com.example.Backend.Controller;


import com.example.Backend.Entity.Category;
import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Services.CategoryService;
import com.example.Backend.Services.EmployeeService;
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
    private EmployeeService employeeService;
    @Autowired
    public LostAndFoundController(LostAndFoundService lostAndFoundService,EmployeeService employeeService){
        this.lostAndFoundService=lostAndFoundService;
        this.employeeService=employeeService;
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

        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public LostAndFoundItem add(@RequestBody LostAndFoundItem lostAndFoundItem){

        for (String token:employeeService.getEmpDeviceToken())
        {
            notificationService.sendNotification("lostAndFound",lostAndFoundItem.getTitle(),token);
        }
        return lostAndFoundService.addlost(lostAndFoundItem);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody LostAndFoundItem lostAndFoundItem ){

        lostAndFoundService.update(lostAndFoundItem);
        notificationService.sendNotification(lostAndFoundItem.getTitle(),lostAndFoundItem.getOwner().getFirstname()+lostAndFoundItem.getOwner().getLastname(),lostAndFoundItem.getFinder().getDeviceToken());

    }


}
