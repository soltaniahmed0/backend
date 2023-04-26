package com.example.Backend.Controller;

import com.example.Backend.Entity.Events;
import com.example.Backend.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    EventService eventService;
    @PostMapping("addEvent/{employeeId}")
    public ResponseEntity<String> addEvent(@RequestBody Events events, @PathVariable int employeeId){
        try {
            eventService.addEvent(events,employeeId);
            return  ResponseEntity.ok("added");
        }
        catch (Exception e){
            return  new ResponseEntity<>("Error addidng the events"+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("GetEvent/{event_id}")
    public  ResponseEntity<Events>getEvent(@PathVariable int event_id){
        try {
            Events event=eventService.getEvent(event_id);
            return ResponseEntity.ok(event);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("updateEvent/{event_id}")
    public ResponseEntity<?> updateEvent(@PathVariable int event_id,@RequestBody Events updatedEvent){
        try {
            Events events=eventService.updateEvent(updatedEvent,event_id);
            return ResponseEntity.ok(events);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }
    @GetMapping("getallEvents")
    public ResponseEntity<List<Events>>getAllEvents(){
        try {
            List<Events>eventsList=eventService.getAllEvents();
            return ResponseEntity.ok(eventsList);
        }
        catch (Exception e)
        {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("deleteEvent/{event_id}")
    public ResponseEntity<String>deletEvent(@PathVariable int event_id){
        try {
            eventService.deleteEvent(event_id);
            return ResponseEntity.noContent().build();

        }
        catch (Exception e)
        {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("updateEventApproval/{eventId}")
    public ResponseEntity<Void> updateEventApproval(@PathVariable int eventId, @RequestParam boolean approve) {
        try {
            eventService.updateEventApproval(eventId, approve);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("getEvents")
    public ResponseEntity<List<Events>> getEvents() {
        try {
            List<Events> events = eventService.getAllEvents().stream()
                    .filter(event -> event.isApprove())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
