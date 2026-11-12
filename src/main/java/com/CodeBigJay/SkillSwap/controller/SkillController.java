package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.dto.*;
import com.CodeBigJay.SkillSwap.model.*;
import com.CodeBigJay.SkillSwap.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/skills")
public class SkillController {
 private final SkillService skills;private final UserService users;
 public SkillController(SkillService skills,UserService users){this.skills=skills;this.users=users;}
 @GetMapping public List<SkillResponse> mine(Authentication a){return skills.getSkillsForUser(users.getByUsername(a.getName()).getId()).stream().map(this::toResponse).toList();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public SkillResponse add(@Valid @RequestBody SkillRequest request,Authentication a){
  User owner=users.getByUsername(a.getName());Skill s=new Skill();s.setName(request.name().trim());s.setLevel(request.level());s.setType(request.type());s.setUser(owner);return toResponse(skills.createSkill(s));
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id,Authentication a){
  try{skills.deleteSkillForUser(id,users.getByUsername(a.getName()).getId());}catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());}
 }
 private SkillResponse toResponse(Skill s){return new SkillResponse(s.getId(),s.getName(),s.getLevel(),s.getType());}
}