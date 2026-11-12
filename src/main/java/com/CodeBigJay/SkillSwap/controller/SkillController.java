package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.model.*;
import com.CodeBigJay.SkillSwap.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/skills")
public class SkillController {
 private final SkillService skills; private final UserService users;
 public SkillController(SkillService skills,UserService users){this.skills=skills;this.users=users;}
 @GetMapping public List<Skill> mine(Authentication a){return skills.getSkillsForUser(users.getByUsername(a.getName()).getId());}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Skill add(@RequestBody Skill input,Authentication a){
  User user=users.getByUsername(a.getName());Skill s=new Skill();s.setName(input.getName());s.setLevel(input.getLevel());s.setType(input.getType());s.setUser(user);return skills.createSkill(s);
 }
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id,Authentication a){
  try{skills.deleteSkillForUser(id,users.getByUsername(a.getName()).getId());}catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());}
 }
}