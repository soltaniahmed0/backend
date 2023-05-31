package com.example.Backend.Services;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    public NotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void sendNotification(String title, String body) {
        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(body).setImage("https://via.placeholder.com/500x500").build())
                .setToken("eA2-hj38QmynX3rseHW8yC:APA91bFwTAQtpDVF-U7WfNuEiHZeLyZYcm-OD32QbLkpvqbyp0Ev0wtbIcfcgIWDzg3uMgTIbmdF6On64K5rSQ7NevBM-QsO6cJTpLDwx6Qc1BHkVqOM9mIu6dnktz-QjnsZ646hP3fz")
                .build();


        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            // Handle the exception
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
    public void sendNotificationToAll(String title, String body) {
        Message message = Message.builder()
                .setNotification(Notification.builder().setTitle(title).setBody(body).setImage("https://via.placeholder.com/500x500").build())
                .setTopic("allDevices")
                .build();


        try {
            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            // Handle the exception
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}
