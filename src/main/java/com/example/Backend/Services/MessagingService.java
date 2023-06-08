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
            res.add(new Messaging_chanelDTO(messaging_chanel.getChannel_id(),user,MessageData.toDTOList(messageData),messaging_chanel.getUnreadMessageCount()));
        });

        return res;
    }
    public List<Messaging_chanel> getMessagingChannels() {
        return messagingChannelRepository.findAll();
    }
    public Messaging_chanel GetChannelById(Long id) {
        return messagingChannelRepository.findByChannel_id(id);
    }
    public Messaging_chanel updateUnreadMessage(Long id,int val) {
        Messaging_chanel existingChannel = GetChannelById(id);

        if (existingChannel != null) {
            existingChannel.setUnreadMessageCount(val);
            // Apply other modifications as needed

            // Save the updated channel to the repository
            return messagingChannelRepository.save(existingChannel);
        }

        // Handle the case when the existing channel is not found
        // You can throw an exception or return null, depending on your requirement
        return null;
    }


    public Messaging_chanel addChannel(Messaging_chanel messaging_chanel){

        return  messagingChannelRepository.save(messaging_chanel);
        }
    public void SendMessage(MessageData messageData){
        messagingDataRepository.save(messageData);
    }



}
