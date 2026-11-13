package com.CodeBigJay.SkillSwap.repository;
import com.CodeBigJay.SkillSwap.model.Notification;
import com.CodeBigJay.SkillSwap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface NotificationRepository extends JpaRepository<Notification,Long>{
 List<Notification> findByUserAndIsReadFalse(User user);
 List<Notification> findByUser(User user);
 Optional<Notification> findByIdAndUser_Id(Long id,Long userId);
}