package com.CodeBigJay.SkillSwap.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank @Size(min=3,max=40) String username,@NotBlank @Email @Size(max=120) String email,@NotBlank @Size(min=8,max=72) String password) {}