package com.CodeBigJay.SkillSwap.repository;
import com.CodeBigJay.SkillSwap.model.SkillExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SkillExchangeRepository extends JpaRepository<SkillExchange,Long>{
 List<SkillExchange> findByRequester_IdOrProvider_IdOrderByScheduledAtDesc(Long requesterId,Long providerId);
}