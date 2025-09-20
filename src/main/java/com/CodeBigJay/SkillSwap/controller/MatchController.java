package com.CodeBigJay.SkillSwap.controller;

import com.CodeBigJay.SkillSwap.dto.MatchResult;
import com.CodeBigJay.SkillSwap.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MatchResult>> find(@PathVariable Long userId) {
        return ResponseEntity.ok(matchService.findMatches(userId));
    }
}
