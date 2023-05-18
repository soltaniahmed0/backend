package com.example.Backend.Controller;


import com.example.Backend.Entity.Room;
import com.example.Backend.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Rooms")
@RestController
public class RoomController {
    private RoomService roomService;
    @Autowired
    public RoomController(RoomService roomService){
        this.roomService=roomService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getitem")
    public Room getItem(@RequestParam int id){
         return roomService.getroom(id);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<Room> getItems(){
        List<Room> res=roomService.getrooms();
        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Room room ){
        System.out.println(room.getType());
        roomService.addroom(room);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Room room ){

        roomService.update(room);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
       roomService.deleteroom(id);

    }

}
