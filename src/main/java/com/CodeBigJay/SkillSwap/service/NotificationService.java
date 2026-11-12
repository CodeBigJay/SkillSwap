package com.CodeBigJay.SkillSwap.service;
import com.CodeBigJay.SkillSwap.model.*;
import com.CodeBigJay.SkillSwap.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class NotificationService {
 private final NotificationRepository notifications;private final UserRepository users;
 public NotificationService(NotificationRepository notifications,UserRepository users){this.notifications=notifications;this.users=users;}
 public Notification sendNotification(Long userId,String message,NotificationType type){
  User user=users.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found."));
  Notification n=new Notification();n.setUser(user);n.setMessage(message);n.setType(type);return notifications.save(n);
 }
 @Transactional(readOnly=true) public List<Notification> forUser(Long userId){User u=users.findById(userId).orElseThrow(()->new IllegalArgumentException("User not found."));return notifications.findByUser(u);}
 @Transactional public Notification markAsRead(Long notificationId,Long userId){
  Notification n=notifications.findByIdAndUser_Id(notificationId,userId).orElseThrow(()->new IllegalArgumentException("Notification not found."));
  n.setRead(true);return notifications.save(n);
 }
}