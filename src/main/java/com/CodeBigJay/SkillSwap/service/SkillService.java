package com.CodeBigJay.SkillSwap.service;
import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.repository.SkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class SkillService {
 private final SkillRepository repository;
 public SkillService(SkillRepository repository){this.repository=repository;}
 public Skill createSkill(Skill skill){return repository.save(skill);}
 public List<Skill> getAllSkills(){return repository.findAll();}
 public List<Skill> getSkillsForUser(Long userId){return repository.findByUser_Id(userId);}
 @Transactional public void deleteSkillForUser(Long skillId,Long userId){
  Skill s=repository.findById(skillId).orElseThrow(()->new IllegalArgumentException("Skill not found."));
  if(s.getUser()==null||!s.getUser().getId().equals(userId))throw new IllegalArgumentException("You can only remove your own skills.");
  repository.delete(s);
 }
}