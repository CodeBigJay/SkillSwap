package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.dto.MatchResult;
import com.CodeBigJay.SkillSwap.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/matches")
public class MatchController {
 private final MatchService matches;private final UserService users;
 public MatchController(MatchService matches,UserService users){this.matches=matches;this.users=users;}
 @GetMapping public List<MatchResult> mine(Authentication a){return matches.findMatches(users.getByUsername(a.getName()).getId());}
}