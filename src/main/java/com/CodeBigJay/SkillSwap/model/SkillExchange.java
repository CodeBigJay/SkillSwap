package com.CodeBigJay.SkillSwap.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="skill_exchanges")
public class SkillExchange {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="requester_id",nullable=false) private User requester;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="provider_id",nullable=false) private User provider;
 @Column(nullable=false,length=80) private String requestedSkill;
 @Column(nullable=false,length=80) private String offeredSkill;
 @Column(nullable=false) private LocalDateTime scheduledAt;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private ExchangeStatus status=ExchangeStatus.PENDING;
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public User getRequester(){return requester;} public void setRequester(User requester){this.requester=requester;}
 public User getProvider(){return provider;} public void setProvider(User provider){this.provider=provider;}
 public String getRequestedSkill(){return requestedSkill;} public void setRequestedSkill(String requestedSkill){this.requestedSkill=requestedSkill;}
 public String getOfferedSkill(){return offeredSkill;} public void setOfferedSkill(String offeredSkill){this.offeredSkill=offeredSkill;}
 public LocalDateTime getScheduledAt(){return scheduledAt;} public void setScheduledAt(LocalDateTime scheduledAt){this.scheduledAt=scheduledAt;}
 public ExchangeStatus getStatus(){return status;} public void setStatus(ExchangeStatus status){this.status=status;}
}