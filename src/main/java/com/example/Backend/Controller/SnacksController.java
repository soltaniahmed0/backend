package com.example.Backend.Controller;


import com.example.Backend.Entity.LostAndFoundItem;
import com.example.Backend.Entity.Snack;
import com.example.Backend.Services.LostAndFoundService;
import com.example.Backend.Services.SnacksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/snacks")
@RestController
public class SnacksController {
    private SnacksService snacksService;
    @Autowired
    public SnacksController(SnacksService snacksService){
        this.snacksService=snacksService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getitem")
    public Snack getItem(@RequestParam int id){
         return snacksService.getsnack(id);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<Snack> getItems(){
        List<Snack> res=snacksService.getsnaks();
        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Snack snack ){

        snacksService.addsnak(snack);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Snack snack ){

        snacksService.update(snack);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
       snacksService.deleteSnack(id);

    }

}
