package com.example.Backend.Services;

import com.example.Backend.Entity.Appointment;
import com.example.Backend.Entity.Room;
import com.example.Backend.Repository.AppointmentRepository;
import com.example.Backend.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getappointments() {
        return appointmentRepository.findAll();
    }

    public void addappointment(Appointment appointment ){
        appointmentRepository.save(appointment);
        }

    public Appointment getappointment(int id) {
        return  appointmentRepository.findById(id).orElse(null);
    }

    public Appointment updateAppointment(Appointment appointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointment.getId());
        if (appointmentOptional.isPresent()) {
            Appointment existingAppointment = appointmentOptional.get();
            existingAppointment.setStartTime(appointment.getStartTime());
            existingAppointment.setEndTime(appointment.getEndTime());
            existingAppointment.setSubject(appointment.getSubject());
            existingAppointment.setNotes(appointment.getNotes());
            existingAppointment.setApprove(appointment.isApprove());
            return appointmentRepository.save(existingAppointment);
        } else {
            throw new IllegalArgumentException("Appointment with id " + appointment.getId() + " not found");
        }
    }

    public void deleteappointment(int id){
        appointmentRepository.deleteById(id);
    }


}
