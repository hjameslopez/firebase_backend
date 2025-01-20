package com.prueba.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.prueba.model.NotificationRequest;
import com.prueba.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    @Autowired
    private FCMService fcmService;

    @PostMapping("/token")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request){
        try{
            String response = fcmService.sendNotification(
                    request.getToken(),
                    request.getTitle(),
                    request.getBody()
            );
            return ResponseEntity.ok(response);
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.internalServerError().body("Failed to send notification:"+e.getMessage());
        }
    }

    @PostMapping("/topic/{topic}")
    public ResponseEntity<String> sendNotificationTotopic(
            @PathVariable String topic,
            @RequestBody NotificationRequest request){
        try{
            String response = fcmService.sendNotificationToTopic(
                    topic,
                    request.getTitle(),
                    request.getBody()
            );
            return ResponseEntity.ok(response);
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.internalServerError().body("Failed to send notification"+e.getMessage());
        }
    }
}
