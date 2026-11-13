package com.CodeBigJay.SkillSwap.repository;
import com.CodeBigJay.SkillSwap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long>{
 User findByUsername(String username);
 boolean existsByUsernameIgnoreCase(String username);
 boolean existsByEmailIgnoreCase(String email);
}