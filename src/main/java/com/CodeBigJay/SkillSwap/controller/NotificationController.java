package com.CodeBigJay.SkillSwap.controller;

import com.CodeBigJay.SkillSwap.model.Notification;
import com.CodeBigJay.SkillSwap.model.NotificationType;
import com.CodeBigJay.SkillSwap.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send/{userId}")
    public Notification sendNotification(
            @PathVariable Long userId,
            @RequestParam String message,
            @RequestParam NotificationType type) {
        return notificationService.sendNotification(userId, message, type);
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return notificationService.getUserNotifications(userId);
    }

    @PutMapping("/{notificationId}/read")
    public Notification markAsRead(@PathVariable Long notificationId) {
        return notificationService.markAsRead(notificationId);
    }
}
