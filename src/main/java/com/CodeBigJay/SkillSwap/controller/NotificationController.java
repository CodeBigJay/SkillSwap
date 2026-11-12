package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.dto.NotificationResponse;
import com.CodeBigJay.SkillSwap.model.Notification;
import com.CodeBigJay.SkillSwap.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/notifications")
public class NotificationController {
 private final NotificationService notifications;private final UserService users;
 public NotificationController(NotificationService notifications,UserService users){this.notifications=notifications;this.users=users;}
 @GetMapping public List<NotificationResponse> mine(Authentication a){Long id=users.getByUsername(a.getName()).getId();return notifications.forUser(id).stream().map(this::toResponse).toList();}
 @PatchMapping("/{id}/read") public NotificationResponse read(@PathVariable Long id,Authentication a){
  try{return toResponse(notifications.markAsRead(id,users.getByUsername(a.getName()).getId()));}catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());}
 }
 private NotificationResponse toResponse(Notification n){return new NotificationResponse(n.getId(),n.getMessage(),n.getType(),n.isRead(),n.getCreatedAt());}
}