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
@RestController @RequestMapping("/api/exchanges")
public class SkillExchangeController {
 private final SkillExchangeService exchanges;private final UserService users;
 public SkillExchangeController(SkillExchangeService exchanges,UserService users){this.exchanges=exchanges;this.users=users;}
 @GetMapping public List<ExchangeResponse> mine(Authentication a){Long id=users.getByUsername(a.getName()).getId();return exchanges.forMember(id).stream().map(this::toResponse).toList();}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public ExchangeResponse create(@Valid @RequestBody CreateExchangeRequest request,Authentication a){
  try{return toResponse(exchanges.create(users.getByUsername(a.getName()).getId(),request));}catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());}
 }
 @PatchMapping("/{id}/status") public ExchangeResponse status(@PathVariable Long id,@RequestParam ExchangeStatus value,Authentication a){
  try{return toResponse(exchanges.changeStatus(id,users.getByUsername(a.getName()).getId(),value));}catch(IllegalArgumentException e){throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());}
 }
 private ExchangeResponse toResponse(SkillExchange e){return new ExchangeResponse(e.getId(),e.getRequester().getId(),e.getRequester().getUsername(),e.getProvider().getId(),e.getProvider().getUsername(),e.getRequestedSkill(),e.getOfferedSkill(),e.getScheduledAt(),e.getStatus());}
}