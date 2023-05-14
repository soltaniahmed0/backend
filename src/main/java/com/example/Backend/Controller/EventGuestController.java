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

import java.util.List;
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
            @RequestParam boolean going) {
        Optional<StartupEvent> startupEventOptional=eventService.getEvent(event_id);
        Optional<Employee> optionalEmployee=employeeService.getEmployeeById(emp_id);
        if (startupEventOptional.isPresent() && optionalEmployee.isPresent()) {
            StartupEvent event = startupEventOptional.get();
            Employee employee = optionalEmployee.get();
            Optional<EventGuest> optionalGuest = eventGuestService.findEventandEmployee(event, employee);
            if (optionalGuest.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            } else {

                return ResponseEntity.ok(eventGuestService.addGuest(event,employee,interested,going));
            }
        } else {
            return ResponseEntity.notFound().build();
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
    @PutMapping("UpdateGuestEventStatus/{id}/{interested}/{going}")
    public ResponseEntity<EventGuest>updateEvent(@PathVariable Integer id,@PathVariable boolean interested,@PathVariable boolean going)
    {

        return ResponseEntity.ok(eventGuestService.EventGuestupdate(id, interested, going));
    }

}
