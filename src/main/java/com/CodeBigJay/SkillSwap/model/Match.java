package com.CodeBigJay.SkillSwap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who requested the match
    @ManyToOne
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    // The user who was matched
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    private boolean confirmed;

    // Constructors
    public Match() {}

    public Match(User requester, User receiver, Skill skill, boolean confirmed) {
        this.requester = requester;
        this.receiver = receiver;
        this.skill = skill;
        this.confirmed = confirmed;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
