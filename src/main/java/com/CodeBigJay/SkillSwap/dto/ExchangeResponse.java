package com.CodeBigJay.SkillSwap.dto;
import com.CodeBigJay.SkillSwap.model.ExchangeStatus;
import java.time.LocalDateTime;
public record ExchangeResponse(Long id,Long requesterId,String requesterUsername,Long providerId,String providerUsername,String requestedSkill,String offeredSkill,LocalDateTime scheduledAt,ExchangeStatus status) {}