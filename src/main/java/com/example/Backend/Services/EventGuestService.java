package com.example.Backend.Services;


import com.example.Backend.Entity.Employee;
import com.example.Backend.Entity.EventGuest;
import com.example.Backend.Entity.StartupEvent;
import com.example.Backend.Repository.EventGuestRepository;
import com.example.Backend.Repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventGuestService {
    @Autowired
    private EventGuestRepository eventGuestRepository;
    public EventGuest addGuest(EventGuest eventGuest) {
        return eventGuestRepository.save(eventGuest);
    }
    public List<EventGuest> getAllEventsGuest(){
        return eventGuestRepository.findAll();
    }
    public Optional<EventGuest> findEventandEmployee(StartupEvent startupEvent, Employee  employee)
    {
        return eventGuestRepository.findByEventAndEmployee(startupEvent,employee);
    }
    public EventGuest addGuest(StartupEvent event,Employee employee,boolean interested,boolean going,boolean paid)
    {
        EventGuest newGuest = new EventGuest();
        newGuest.setEvent(event);
        newGuest.setEmployee(employee);
        newGuest.setInterested(interested);
        newGuest.setGoing(going);
        newGuest.setPaid(paid);
        EventGuest savedGuest = eventGuestRepository.save(newGuest);
        return savedGuest;
    }
    public EventGuest EventGuestupdate(Integer eventGuestid,boolean interested,boolean going,boolean paid)
    {
        Optional<EventGuest> optionalEventGuest= eventGuestRepository.findById(eventGuestid);
        if (optionalEventGuest.isPresent()) {
            EventGuest eventGuest = optionalEventGuest.get();
            eventGuest.setInterested(interested);
            eventGuest.setGoing(going);
            eventGuest.setPaid(paid);
            EventGuest savedEventGuest = eventGuestRepository.save(eventGuest);
            return savedEventGuest;
        } else {
            throw new EntityNotFoundException("EventGuest not found with ID: " + eventGuestid);
        }
    }
    public List<Employee> getEmployeesByEventAndInterestedOrGoing(StartupEvent event) {
        return eventGuestRepository.findEmployeesByEventAndInterestedOrGoing(event);
    }
    @Transactional
    public void deleteEmployeesByEvent(StartupEvent event) {
        eventGuestRepository.deleteByEvent(event);
    }
    public  List<Employee> GetGuestEvent(Integer id)
    {
        Optional<EventGuest>optionalEventGuest=eventGuestRepository.findById(id);
        if(optionalEventGuest.isPresent()){
            EventGuest eventGuest=optionalEventGuest.get();
            List<Employee> guests=eventGuestRepository.getGuest(eventGuest.getId());
            return guests;
        }
        else {
            throw new EntityNotFoundException("No EventGuest with Id"+id);
        }
    }

}
