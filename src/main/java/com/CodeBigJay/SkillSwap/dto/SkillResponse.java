package com.CodeBigJay.SkillSwap.dto;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
public record SkillResponse(Long id,String name,String level,UserSkillType type) {}