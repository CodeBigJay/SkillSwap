package com.CodeBigJay.SkillSwap.controller;
import com.CodeBigJay.SkillSwap.dto.*;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final UserService users;
 public AuthController(UserService users){this.users=users;}
 @PostMapping("/register") @ResponseStatus(HttpStatus.CREATED)
 public UserResponse register(@Valid @RequestBody RegisterRequest r){
  try{User u=users.register(r.username(),r.email(),r.password());return new UserResponse(u.getId(),u.getUsername(),u.getEmail());}
  catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());}
 }
 @GetMapping("/me") public UserResponse me(Authentication a){User u=users.getByUsername(a.getName());return new UserResponse(u.getId(),u.getUsername(),u.getEmail());}
}