package com.CodeBigJay.SkillSwap.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity @Table(name="users",uniqueConstraints={@UniqueConstraint(name="uk_users_username",columnNames="username"),@UniqueConstraint(name="uk_users_email",columnNames="email")})
public class User {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=40) private String username;
 @Column(nullable=false,length=120) private String email;
 @JsonIgnore @Column(nullable=false,length=100) private String password;
 @JsonIgnore @OneToMany(mappedBy="user",cascade=CascadeType.ALL) private List<Skill> skills=new ArrayList<>();
 @JsonIgnore @OneToMany(mappedBy="user",cascade=CascadeType.ALL) private List<Notification> notifications=new ArrayList<>();
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getUsername(){return username;} public void setUsername(String username){this.username=username;}
 public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
 public String getPassword(){return password;} public void setPassword(String password){this.password=password;}
 public List<Skill> getSkills(){return skills;} public void setSkills(List<Skill> skills){this.skills=skills;}
 public List<Notification> getNotifications(){return notifications;} public void setNotifications(List<Notification> notifications){this.notifications=notifications;}
}