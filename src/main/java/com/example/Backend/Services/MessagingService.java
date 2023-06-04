package com.example.Backend.Services;


import com.example.Backend.Entity.*;
import com.example.Backend.Repository.MessagingChannelRepository;
import com.example.Backend.Repository.MessagingDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagingService {
    @Autowired
    private MessagingChannelRepository messagingChannelRepository;
    @Autowired
    private MessagingDataRepository messagingDataRepository;

    public List<Messaging_chanelDTO> getuserMessagingChannels(Long id) {
        List<Messaging_chanelDTO> res = new ArrayList<>();
         messagingChannelRepository.findByUserIdOrUser1Id(id).forEach(messaging_chanel -> {

             List<MessageData> messageData= messagingDataRepository.findByChannel_Id(messaging_chanel.getChannel_id());
             Employee user = messaging_chanel.getUser().getEmployee_id()!=id?messaging_chanel.getUser():messaging_chanel.getUser1();
            res.add(new Messaging_chanelDTO(messaging_chanel.getChannel_id(),user,MessageData.toDTOList(messageData)));
        });

        return res;
    }
    public List<Messaging_chanel> getMessagingChannels() {
        return messagingChannelRepository.findAll();
    }
    public Messaging_chanel GetChannelById(Long id) {
        return messagingChannelRepository.findByChannel_id(id);
    }

    public void addChannel(Messaging_chanel messaging_chanel){
        messagingChannelRepository.save(messaging_chanel);
        }
    public void SendMessage(MessageData messageData){
        messagingDataRepository.save(messageData);
    }


        /*
    public Appointment getappointment(int id) {
        return  appointmentRepository.findById(id).orElse(null);
    }
    public List<Appointment> getAppointmentsByRoomId(int roomId) {
        Optional<Room> findroom=roomRepository.findById(roomId);
        if(findroom.isPresent()){
            Room room=findroom.get();
            return appointmentRepository.findByRoom(room);
        }
        else{
            throw new IllegalArgumentException("room with id " + roomRepository+ " not found");
        }
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

*/
}
