package com.example.Backend.Controller;

import com.example.Backend.Entity.Appointment;
import com.example.Backend.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Appointment")
@RestController
public class AppointmentController {
    private AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService=appointmentService;
    }
    @CrossOrigin(origins = "http://localhost:57384")
    @GetMapping("/getitem")
    public Appointment getItem(@RequestParam int id){
         return appointmentService.getappointment(id);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @GetMapping("/getitems")

    public List<Appointment> getItems(){
        List<Appointment> res=appointmentService.getappointments();
        return res;
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PostMapping("/add")
    public void add(@RequestBody Appointment appointment ){
        appointmentService.addappointment(appointment);
    }

    @CrossOrigin(origins = "http://localhost:59838/")
    @PutMapping("/update")
    public void update(@RequestBody Appointment appointment ){

        appointmentService.updateAppointment(appointment);

    }
    @CrossOrigin(origins = "http://localhost:59838/")
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        appointmentService.deleteappointment(id);

    }

}
