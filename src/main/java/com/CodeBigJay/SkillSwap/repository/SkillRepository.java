package com.CodeBigJay.SkillSwap.repository;

import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByUser_Id(Long userId);
    List<Skill> findByUser_IdAndType(Long userId, UserSkillType type);
}
