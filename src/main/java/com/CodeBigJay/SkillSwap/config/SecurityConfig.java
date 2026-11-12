package com.CodeBigJay.SkillSwap.config;
import com.CodeBigJay.SkillSwap.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.*;
import java.util.List;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean DaoAuthenticationProvider authenticationProvider(UserService users,PasswordEncoder encoder){
  DaoAuthenticationProvider p=new DaoAuthenticationProvider();p.setUserDetailsService(users);p.setPasswordEncoder(encoder);return p;
 }
 @Bean SecurityFilterChain filterChain(HttpSecurity http,DaoAuthenticationProvider provider)throws Exception{
  return http.csrf(c->c.disable()).cors(Customizer.withDefaults())
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authenticationProvider(provider).authorizeHttpRequests(a->a.requestMatchers(HttpMethod.POST,"/api/auth/register").permitAll().anyRequest().authenticated())
   .httpBasic(Customizer.withDefaults()).build();
 }
 @Bean CorsConfigurationSource corsConfigurationSource(){
  CorsConfiguration c=new CorsConfiguration();c.setAllowedOrigins(List.of("http://localhost:5173"));c.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));c.setAllowCredentials(true);
  UrlBasedCorsConfigurationSource s=new UrlBasedCorsConfigurationSource();s.registerCorsConfiguration("/**",c);return s;
 }
}