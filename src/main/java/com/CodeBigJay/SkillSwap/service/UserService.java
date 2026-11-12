package com.CodeBigJay.SkillSwap.service;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class UserService implements UserDetailsService {
 private final UserRepository repository; private final PasswordEncoder encoder;
 public UserService(UserRepository repository,PasswordEncoder encoder){this.repository=repository;this.encoder=encoder;}
 @Override public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
  User u=repository.findByUsername(username);if(u==null)throw new UsernameNotFoundException("User not found");
  return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).roles("USER").build();
 }
 @Transactional public User register(String username,String email,String password){
  if(repository.existsByUsernameIgnoreCase(username))throw new IllegalArgumentException("Username is already registered.");
  if(repository.existsByEmailIgnoreCase(email))throw new IllegalArgumentException("Email is already registered.");
  User u=new User();u.setUsername(username.trim());u.setEmail(email.trim().toLowerCase());u.setPassword(encoder.encode(password));return repository.save(u);
 }
 public User getByUsername(String username){User u=repository.findByUsername(username);if(u==null)throw new UsernameNotFoundException("User not found");return u;}
 public User getUserById(Long id){return repository.findById(id).orElse(null);}
 public List<User> getAllUsers(){return repository.findAll();}
}