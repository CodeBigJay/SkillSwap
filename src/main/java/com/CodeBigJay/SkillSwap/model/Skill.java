package com.CodeBigJay.SkillSwap.model;

import jakarta.persistence.*;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String level; // Beginner, Intermediate, Advanced

    @Enumerated(EnumType.STRING)
    private UserSkillType type; // OFFERED or WANTED

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public UserSkillType getType() { return type; }
    public void setType(UserSkillType type) { this.type = type; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
