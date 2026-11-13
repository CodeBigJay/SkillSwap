package com.CodeBigJay.SkillSwap.dto;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
import jakarta.validation.constraints.*;
public record SkillRequest(@NotBlank @Size(max=80) String name,@Size(max=40) String level,@NotNull UserSkillType type) {}