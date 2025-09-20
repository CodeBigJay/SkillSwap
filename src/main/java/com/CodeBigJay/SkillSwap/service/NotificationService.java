package com.CodeBigJay.SkillSwap.service;

import com.CodeBigJay.SkillSwap.model.Notification;
import com.CodeBigJay.SkillSwap.model.NotificationType;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.repository.NotificationRepository;
import com.CodeBigJay.SkillSwap.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification sendNotification(Long userId, String message, NotificationType type) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            Notification notification = new Notification();
            notification.setUser(user.get());
            notification.setMessage(message);
            notification.setType(type);
            return notificationRepository.save(notification);
        }
        throw new RuntimeException("User not found");
    }

    public List<Notification> getUserNotifications(Long userId) {
        return userRepository.findById(userId)
                .map(notificationRepository::findByUser)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Notification markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(n -> {
            n.setRead(true);
            return notificationRepository.save(n);
        }).orElseThrow(() -> new RuntimeException("Notification not found"));
    }
}
