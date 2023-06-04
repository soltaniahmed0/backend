package com.example.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MessageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "employee_id")
    private Employee sender;
    private String message;
    private LocalDateTime messageDate;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Messaging_chanel channel;

    public MessageData(Employee sender, String message, LocalDateTime messageDate, Messaging_chanel channel) {
        this.sender = sender;
        this.message = message;
        this.messageDate = messageDate;
        this.channel = channel;
    }

    public static List<MessageDataDTO> toDTOList(List<MessageData> entities) {
        List<MessageDataDTO> dtos = new ArrayList<>();
        for (MessageData entity : entities) {
            MessageDataDTO dto = new MessageDataDTO();
            dto.setSender(entity.sender);
            dto.setMessage(entity.message);
            dto.setMessageDate(entity.messageDate);
            dto.setChannel(entity.channel.getChannel_id());
            dtos.add(dto);
        }
        return dtos;
    }
}






