package com.CodeBigJay.SkillSwap.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SkillExchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User requester;

    @ManyToOne
    private User provider;

    private String requestedSkill;
    private String offeredSkill;
    private LocalDateTime scheduledAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getRequester() { return requester; }
    public void setRequester(User requester) { this.requester = requester; }

    public User getProvider() { return provider; }
    public void setProvider(User provider) { this.provider = provider; }

    public String getRequestedSkill() { return requestedSkill; }
    public void setRequestedSkill(String requestedSkill) { this.requestedSkill = requestedSkill; }

    public String getOfferedSkill() { return offeredSkill; }
    public void setOfferedSkill(String offeredSkill) { this.offeredSkill = offeredSkill; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
}
