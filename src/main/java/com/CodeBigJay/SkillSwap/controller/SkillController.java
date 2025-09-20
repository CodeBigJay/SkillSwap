package com.CodeBigJay.SkillSwap.controller;

import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
import com.CodeBigJay.SkillSwap.service.SkillService;
import com.CodeBigJay.SkillSwap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;
    private final UserService userService; // ✅ FIX: added UserService

    @Autowired
    public SkillController(SkillService skillService, UserService userService) {
        this.skillService = skillService;
        this.userService = userService;
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }


    @PostMapping("/for-user/{userId}")
    public Skill addSkillForUser(@PathVariable Long userId,
                                 @RequestParam String name,
                                 @RequestParam(required = false) String level,
                                 @RequestParam UserSkillType type) {
        Skill s = new Skill();
        s.setName(name);
        s.setLevel(level);
        s.setType(type);

        // FIX: now userService is available
        User u = userService.getUserById(userId);
        if (u == null) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        s.setUser(u);
        return skillService.createSkill(s);
    }
}
