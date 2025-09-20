package com.CodeBigJay.SkillSwap.dto;

import java.util.List;

public class MatchResult {
    private Long userId;
    private String username;
    private int score;
    private List<String> theyOfferYouWant;   // other's OFFERED ∩ my WANTED
    private List<String> theyWantYouOffer;   // other's WANTED ∩ my OFFERED

    public MatchResult(Long userId, String username, int score,
                       List<String> theyOfferYouWant, List<String> theyWantYouOffer) {
        this.userId = userId;
        this.username = username;
        this.score = score;
        this.theyOfferYouWant = theyOfferYouWant;
        this.theyWantYouOffer = theyWantYouOffer;
    }

    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public int getScore() { return score; }
    public List<String> getTheyOfferYouWant() { return theyOfferYouWant; }
    public List<String> getTheyWantYouOffer() { return theyWantYouOffer; }
}
