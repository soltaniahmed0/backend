package com.example.Backend.Entity;



import java.util.List;


public class Messaging_chanelDTO {

    private Long channel_id;
    private Employee user;
    private List<MessageDataDTO> messages;

    public Messaging_chanelDTO(Long channel_id, Employee user, List<MessageDataDTO> messages) {
        this.channel_id = channel_id;
        this.user = user;
        this.messages = messages;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public Employee getUser() {
        return user;
    }

    public void setUser(Employee user) {
        this.user = user;
    }

    public List<MessageDataDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDataDTO> messages) {
        this.messages = messages;
    }
}

