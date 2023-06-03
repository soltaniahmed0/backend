package com.example.Backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDataDTO {
    private Employee sender;
    private String message;
    private LocalDateTime messageDate;
    private Long channel;

    // Create getters and setters for the fields
}
