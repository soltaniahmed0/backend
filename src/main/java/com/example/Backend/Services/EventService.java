package com.example.Backend.Services;

import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.Events;
import com.example.Backend.Repository.EmployeeRepository;
import com.example.Backend.Repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public Events addEvent(Events events,int employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException("Employee with ID " + employeeId + " not found"));
        events.setEmployee(employee);
        return eventRepository.save(events);
    }
    public Events getEvent(int event_id){
        Events events=eventRepository.findById(event_id).orElseThrow(() ->
                new EntityNotFoundException("Event wiht Id"+event_id+"not found"));
        return events;
    }
    public  Events updateEvent(Events events,int event_id){
        Events event=eventRepository.findById(event_id).orElseThrow(() ->
                new EntityNotFoundException("Event wiht Id"+event_id+"not found"));
        event.setEventName(events.getEventName());
        event.setEventDescription(events.getEventDescription());
        event.setEventDate(events.getEventDate());
        event.setEventStartTime(events.getEventStartTime());
        event.setEventEndTime(events.getEventEndTime());
        event.setLocation(events.getLocation());
        event.setApprove(events.isApprove());
        event.setEventimg(events.getEventimg());
        eventRepository.save(event);
        return  event;
    }
    public List<Events>getAllEvents(){
        List<Events> events=eventRepository.findAll();
        return events;
    }
    public String deleteEvent(int event_id){
        try{
            eventRepository.deleteById(event_id);
            return "deleted";
        }

        catch (Exception e)
        {
            return "not deleted";
        }
    }
    public void updateEventApproval(int eventId, boolean approve) {
        Events event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Invalid event id"));
        event.setApprove(approve);
        eventRepository.save(event);
    }
}
