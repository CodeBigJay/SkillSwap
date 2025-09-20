package com.CodeBigJay.SkillSwap.service;

import com.CodeBigJay.SkillSwap.dto.MatchResult;
import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
import com.CodeBigJay.SkillSwap.repository.SkillRepository;
import com.CodeBigJay.SkillSwap.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    public MatchService(UserRepository userRepository, SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    public List<MatchResult> findMatches(Long userId) {
        // My skills
        Set<String> myOffered = skillRepository.findByUser_IdAndType(userId, UserSkillType.OFFERED)
                .stream().map(s -> s.getName().toLowerCase()).collect(Collectors.toSet());
        Set<String> myWanted = skillRepository.findByUser_IdAndType(userId, UserSkillType.WANTED)
                .stream().map(s -> s.getName().toLowerCase()).collect(Collectors.toSet());

        if (myOffered.isEmpty() && myWanted.isEmpty()) return List.of();

        List<User> others = userRepository.findAll().stream()
                .filter(u -> !u.getId().equals(userId))
                .toList();

        List<MatchResult> results = new ArrayList<>();

        for (User other : others) {
            List<Skill> otherOfferedList = skillRepository.findByUser_IdAndType(other.getId(), UserSkillType.OFFERED);
            List<Skill> otherWantedList  = skillRepository.findByUser_IdAndType(other.getId(), UserSkillType.WANTED);

            List<String> overlapA = otherOfferedList.stream()
                    .map(s -> s.getName().toLowerCase())
                    .filter(myWanted::contains)
                    .distinct()
                    .toList();

            List<String> overlapB = otherWantedList.stream()
                    .map(s -> s.getName().toLowerCase())
                    .filter(myOffered::contains)
                    .distinct()
                    .toList();

            if (overlapA.isEmpty() && overlapB.isEmpty()) continue;

            int base = overlapA.size() * 10 + overlapB.size() * 10;
            int multiBonus = Math.max(0, (overlapA.size() + overlapB.size() - 1)) * 5;
            int score = base + multiBonus;

            // Convert back to display names (capitalize as stored originally)
            List<String> theyOfferYouWant = otherOfferedList.stream()
                    .map(Skill::getName)
                    .filter(n -> overlapA.contains(n.toLowerCase()))
                    .distinct()
                    .toList();

            List<String> theyWantYouOffer = otherWantedList.stream()
                    .map(Skill::getName)
                    .filter(n -> overlapB.contains(n.toLowerCase()))
                    .distinct()
                    .toList();

            results.add(new MatchResult(other.getId(), other.getUsername(), score,
                    theyOfferYouWant, theyWantYouOffer));
        }

        results.sort(Comparator.comparingInt(MatchResult::getScore).reversed());
        return results;
    }
}
