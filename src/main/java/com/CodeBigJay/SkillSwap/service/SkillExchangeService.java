package com.CodeBigJay.SkillSwap.service;
import com.CodeBigJay.SkillSwap.dto.CreateExchangeRequest;
import com.CodeBigJay.SkillSwap.model.*;
import com.CodeBigJay.SkillSwap.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class SkillExchangeService {
 private final SkillExchangeRepository exchanges;private final UserRepository users;private final SkillRepository skills;private final NotificationService notifications;
 public SkillExchangeService(SkillExchangeRepository exchanges,UserRepository users,SkillRepository skills,NotificationService notifications){this.exchanges=exchanges;this.users=users;this.skills=skills;this.notifications=notifications;}
 @Transactional public SkillExchange create(Long requesterId,CreateExchangeRequest request){
  User requester=users.findById(requesterId).orElseThrow(()->new IllegalArgumentException("Your account could not be found."));
  User provider=users.findById(request.providerId()).orElseThrow(()->new IllegalArgumentException("The selected member could not be found."));
  if(requesterId.equals(provider.getId()))throw new IllegalArgumentException("You cannot request an exchange with yourself.");
  boolean requesterOffers=skills.findByUser_IdAndType(requesterId,UserSkillType.OFFERED).stream().anyMatch(s->s.getName().equalsIgnoreCase(request.offeredSkill().trim()));
  boolean providerOffers=skills.findByUser_IdAndType(provider.getId(),UserSkillType.OFFERED).stream().anyMatch(s->s.getName().equalsIgnoreCase(request.requestedSkill().trim()));
  if(!requesterOffers||!providerOffers)throw new IllegalArgumentException("Choose skills that are listed as offered by the respective members.");
  SkillExchange exchange=new SkillExchange();exchange.setRequester(requester);exchange.setProvider(provider);exchange.setRequestedSkill(request.requestedSkill().trim());exchange.setOfferedSkill(request.offeredSkill().trim());exchange.setScheduledAt(request.scheduledAt());exchange.setStatus(ExchangeStatus.PENDING);SkillExchange saved=exchanges.save(exchange);notifications.sendNotification(provider.getId(),requester.getUsername()+" requested a skill exchange with you.",NotificationType.EXCHANGE_SCHEDULED);return saved;
 }
 @Transactional(readOnly=true) public List<SkillExchange> forMember(Long userId){return exchanges.findByRequester_IdOrProvider_IdOrderByScheduledAtDesc(userId,userId);}
 @Transactional public SkillExchange changeStatus(Long exchangeId,Long actorId,ExchangeStatus next){
  SkillExchange e=exchanges.findById(exchangeId).orElseThrow(()->new IllegalArgumentException("Exchange not found."));
  boolean requester=e.getRequester().getId().equals(actorId),provider=e.getProvider().getId().equals(actorId);
  if(!requester&&!provider)throw new IllegalArgumentException("You are not a participant in this exchange.");
  if(e.getStatus()!=ExchangeStatus.PENDING&&!(e.getStatus()==ExchangeStatus.ACCEPTED&&next==ExchangeStatus.COMPLETED))throw new IllegalArgumentException("This exchange can no longer be changed.");
  boolean allowed=(provider&&(next==ExchangeStatus.ACCEPTED||next==ExchangeStatus.DECLINED))||(requester&&next==ExchangeStatus.CANCELLED)||((requester||provider)&&e.getStatus()==ExchangeStatus.ACCEPTED&&next==ExchangeStatus.COMPLETED);
  if(!allowed)throw new IllegalArgumentException("You are not allowed to make that status change.");
  e.setStatus(next);SkillExchange saved=exchanges.save(e);String message=switch(next){case ACCEPTED->e.getProvider().getUsername()+" accepted your skill exchange request.";case DECLINED->e.getProvider().getUsername()+" declined your skill exchange request.";case CANCELLED->e.getRequester().getUsername()+" cancelled the skill exchange request.";case COMPLETED->"Your skill exchange with "+(requester?e.getProvider().getUsername():e.getRequester().getUsername())+" was marked completed.";default->"Your skill exchange was updated.";};if(next==ExchangeStatus.COMPLETED){notifications.sendNotification(e.getRequester().getId(),message,NotificationType.SYSTEM);notifications.sendNotification(e.getProvider().getId(),message,NotificationType.SYSTEM);}else{Long recipient=requester?e.getProvider().getId():e.getRequester().getId();notifications.sendNotification(recipient,message,NotificationType.SYSTEM);}return saved;
 }
}