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
                .setToken("cCDIlaGoQC2XCzARCgWOyV:APA91bFhaVgwv16Qn1GMG6Nd9cnUC0vWyRK2zmfulLhkUdhlYKrrm09aVsLoFFuXfzrdrZoCnURZcra3hiIenK3aWXPIRSDKFBH0ymMV7KQgU1QuSDiYCnkbwTU8xoPal2B2a9QDdO2b")
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
