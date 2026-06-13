package com.sdp.logistics.controller;

import com.sdp.logistics.dto.NotificationResponse;
import com.sdp.logistics.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByCustomer(@PathVariable Long customerId) {
        List<NotificationResponse> response = notificationService.getNotificationsByCustomer(customerId);
        return ResponseEntity.ok(response);
    }
}