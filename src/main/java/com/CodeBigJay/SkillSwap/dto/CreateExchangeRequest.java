package com.CodeBigJay.SkillSwap.dto;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
public record CreateExchangeRequest(@NotNull Long providerId,@NotBlank @Size(max=80) String requestedSkill,@NotBlank @Size(max=80) String offeredSkill,@NotNull @Future LocalDateTime scheduledAt) {}