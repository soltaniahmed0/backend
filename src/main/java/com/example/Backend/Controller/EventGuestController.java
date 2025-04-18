package com.example.Backend.Controller;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.EventGuest;
import com.example.Backend.Entity.StartupEvent;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.Repository.EventGuestRepository;
import com.example.Backend.Repository.EventRepository;
import com.example.Backend.Services.EmployeeService;
import com.example.Backend.Services.EventGuestService;
import com.example.Backend.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("EventGuest")
public class EventGuestController {
    @Autowired
    private EventGuestRepository eventGuestRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EventGuestService eventGuestService;
    @PostMapping("addGuest/{event_id}/{emp_id}")
    public ResponseEntity<EventGuest> addEventGuest(
            @PathVariable int event_id,
            @PathVariable int emp_id,
            @RequestParam boolean interested,
            @RequestParam boolean going,
            @RequestParam boolean paid
            ) {
        Optional<StartupEvent> startupEventOptional=eventService.getEvent(event_id);
        Optional<Employee> optionalEmployee=employeeService.getEmployeeById(emp_id);
        if (startupEventOptional.isPresent() && optionalEmployee.isPresent()) {
            StartupEvent event = startupEventOptional.get();
            Employee employee = optionalEmployee.get();
            Optional<EventGuest> optionalGuest = eventGuestService.findEventandEmployee(event, employee);
            if (optionalGuest.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {

                return ResponseEntity.ok(eventGuestService.addGuest(event,employee,interested,going,paid));
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/Guest/{eventId}")
    public ResponseEntity<?> getEmployeesByEventAndInterestedOrGoing(@PathVariable Integer eventId) {
        Optional<StartupEvent> event =eventRepository.findById(eventId);
        if(event.isPresent())
        {
            StartupEvent startupEvent=event.get();
            return ResponseEntity.ok(eventGuestService.getEmployeesByEventAndInterestedOrGoing(startupEvent));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteallGuest/{eventId}")
    public ResponseEntity<String> deleteEmployeesByEvent(@PathVariable Integer eventId) {
        Optional<StartupEvent> event =eventRepository.findById(eventId);
        if(event.isPresent()){
            StartupEvent startupEvent=event.get();

            eventGuestService.deleteEmployeesByEvent(startupEvent);
            return ResponseEntity.ok("Employees deleted successfully");

        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    @GetMapping("Guests/{id}")
    public ResponseEntity<?>getGuests(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(eventGuestService.GetGuestEvent(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("eventGuests")
    public ResponseEntity<List<EventGuest>> getAllEventGuests() {

        return ResponseEntity.ok(eventGuestRepository.findAll());
    }
    @GetMapping("getThisEvent/{event_id}/{emp_id}")
    public ResponseEntity<Optional<EventGuest>> getthisEvent(@PathVariable int event_id,
                                                             @PathVariable int emp_id)
    {       Optional<StartupEvent> startupEventOptional=eventService.getEvent(event_id);
        Optional<Employee> optionalEmployee=employeeService.getEmployeeById(emp_id);
        StartupEvent event = startupEventOptional.get();
        Employee employee = optionalEmployee.get();
        Optional<EventGuest> optionalGuest = eventGuestService.findEventandEmployee(event, employee);
        return ResponseEntity.ok(optionalGuest);
    }
    @PutMapping("UpdateGuestEventStatus/{id}/{interested}/{going}/{paid}")
    public ResponseEntity<EventGuest>updateEventStatus(@PathVariable Integer id,@PathVariable boolean interested,@PathVariable boolean going,@PathVariable boolean paid)
    {

        return ResponseEntity.ok(eventGuestService.EventGuestupdate(id, interested, going,paid));
    }

    @GetMapping("/statistics/{eventId}")
    public ResponseEntity<?> getEventGuestStatistics(@PathVariable Integer eventId) {

        Optional<StartupEvent> event=eventRepository.findById(eventId);
        if (event.isPresent())
        {
            StartupEvent event1=event.get();
            long interestedCount = eventGuestRepository.countByEventAndInterested(event1, true);
            long goingCount = eventGuestRepository.countByEventAndGoing(event1, true);
            long paidCount = eventGuestRepository.countByEventAndPaid(event1, true);
            Map<String, Long> statistics = new HashMap<>();
            statistics.put("interestedCount", interestedCount);
            statistics.put("goingCount", goingCount);
            statistics.put("paidCount", paidCount);
            return ResponseEntity.ok(statistics);
        }
        return ResponseEntity.notFound().build();



    }

}
