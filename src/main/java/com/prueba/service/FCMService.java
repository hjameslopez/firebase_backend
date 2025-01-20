package com.prueba.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FCMService {

    public String sendNotification(String token, String title, String body) throws FirebaseMessagingException{
        Message message = Message.builder()
                .setToken(token)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .putData("saludo","hola")
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message(token): "+ response);
        return response;
    }

    public String sendNotificationToTopic(String topic, String title, String body) throws FirebaseMessagingException{
        Message message = Message.builder()
                .setTopic(topic)
                .setNotification(Notification.builder()
                        .setTitle(title)
                        .setBody(body)
                        .build())
                .putData("saludo","hola")
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message (topic): "+ response);
        return response;
    }
}
