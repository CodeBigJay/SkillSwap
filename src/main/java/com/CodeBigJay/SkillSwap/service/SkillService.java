package com.CodeBigJay.SkillSwap.service;

import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Skill createSkill(Skill skill) { return skillRepository.save(skill); }
    public List<Skill> getAllSkills() { return skillRepository.findAll(); }
}
