package com.CodeBigJay.SkillSwap.dto;
import com.CodeBigJay.SkillSwap.model.NotificationType;
import java.time.LocalDateTime;
public record NotificationResponse(Long id,String message,NotificationType type,boolean read,LocalDateTime createdAt) {}