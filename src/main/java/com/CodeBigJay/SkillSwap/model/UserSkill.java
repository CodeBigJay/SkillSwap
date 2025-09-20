//package com.CodeBigJay.SkillSwap.model;
//
//import com.CodeBigJay.SkillSwap.model.
//
//
//enum.UserSkillType;
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "user_skills",
//        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id","skill_id","type"}))
//public class UserSkill {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="user_id", nullable=false)
//    private User user;
//
//    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="skill_id", nullable=false)
//    private Skill skill;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable=false, length=10)
//    private UserSkillType type; // OFFERED or WANTED
//
//    // getters/setters
//    public Long getId() { return id; }
//    public User getUser() { return user; }
//    public Skill getSkill() { return skill; }
//    public UserSkillType getType() { return type; }
//    public void setId(Long id) { this.id = id; }
//    public void setUser(User user) { this.user = user; }
//    public void setSkill(Skill skill) { this.skill = skill; }
//    public void setType(UserSkillType type) { this.type = type; }
//}
