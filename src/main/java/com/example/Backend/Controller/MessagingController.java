package com.example.Backend.Controller;

import com.example.Backend.Entity.Messaging_chanel;
import com.example.Backend.Entity.Messaging_chanelDTO;
import com.example.Backend.Services.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/Messaging")
@RestController
public class MessagingController {

    @Autowired
    private MessagingService messagingService;
    @Autowired
    public MessagingController(MessagingService messagingService){
        this.messagingService=messagingService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getUserChannels")
    public List<Messaging_chanelDTO> getUserChannels(@RequestParam Long id){
         return messagingService.getuserMessagingChannels(id);
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getChannels")
    public List<Messaging_chanel> getChannels(){
        return messagingService.getMessagingChannels();
    }
    /*@CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/roomappointments/{roomId}")
    public List<Appointment> getAppointmentsByRoomId(@PathVariable int roomId) {
        return appointmentService.getAppointmentsByRoomId(roomId);
    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<Appointment> getItems(){
        List<Appointment> res=appointmentService.getappointments();
        return res;
    }*/

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Messaging_chanel messaging_chanel){
        messagingService.addChannel(messaging_chanel);
    }
/*
    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Appointment appointment ){
        appointmentService.updateAppointment(appointment);

    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/Approve")
    public void Approve(@RequestBody Appointment appointment ){

        notificationService.sendNotification("Reservation ", appointment.getRoom().getRoom_name()+" : "+appointment.getSubject());
        appointmentService.updateAppointment(appointment);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        appointmentService.deleteappointment(id);

    }
*/
}
