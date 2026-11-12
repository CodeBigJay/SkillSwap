package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.dto.UserResponse;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/users")
public class UserController {
 private final UserService users;
 public UserController(UserService users){this.users=users;}
 @GetMapping("/me") public UserResponse me(Authentication a){User u=users.getByUsername(a.getName());return new UserResponse(u.getId(),u.getUsername(),u.getEmail());}
}