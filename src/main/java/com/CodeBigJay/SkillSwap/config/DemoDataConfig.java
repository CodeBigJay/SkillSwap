package com.CodeBigJay.SkillSwap.config;

import com.CodeBigJay.SkillSwap.model.Skill;
import com.CodeBigJay.SkillSwap.model.User;
import com.CodeBigJay.SkillSwap.model.UserSkillType;
import com.CodeBigJay.SkillSwap.repository.SkillRepository;
import com.CodeBigJay.SkillSwap.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creates fictional sample members for local demos only.
 * Activate explicitly with --spring.profiles.active=demo.
 */
@Configuration
@Profile("demo")
public class DemoDataConfig {

    private record DemoMember(String username, String[] offered, String[] wanted) {}

    private static final DemoMember[] MEMBERS = {
        new DemoMember("demo.alex", new String[]{"Python", "Data Analysis"}, new String[]{"UI Design", "Public Speaking"}),
        new DemoMember("demo.sipho", new String[]{"UI Design", "Figma"}, new String[]{"Python", "Excel"}),
        new DemoMember("demo.amara", new String[]{"Graphic Design", "Canva"}, new String[]{"Web Development", "Photography"}),
        new DemoMember("demo.lerato", new String[]{"Excel", "Bookkeeping"}, new String[]{"Graphic Design", "Python"}),
        new DemoMember("demo.noah", new String[]{"Java", "Spring Boot"}, new String[]{"UI Design", "SQL"}),
        new DemoMember("demo.zanele", new String[]{"Photography", "Video Editing"}, new String[]{"Excel", "Public Speaking"}),
        new DemoMember("demo.kabelo", new String[]{"Web Development", "HTML/CSS"}, new String[]{"Java", "Graphic Design"}),
        new DemoMember("demo.mia", new String[]{"Public Speaking", "Writing"}, new String[]{"Data Analysis", "Video Editing"}),
        new DemoMember("demo.thabo", new String[]{"SQL", "Power BI"}, new String[]{"Spring Boot", "Photography"}),
        new DemoMember("demo.ella", new String[]{"Digital Marketing", "Social Media"}, new String[]{"Canva", "Web Development"})
    };

    @Bean
    CommandLineRunner seedDemoMembers(UserRepository users, SkillRepository skills, PasswordEncoder encoder) {
        return args -> {
            // Shared sample password is deliberately limited to the opt-in local demo profile.
            for (DemoMember member : MEMBERS) {
                if (users.existsByUsernameIgnoreCase(member.username())) {
                    continue;
                }

                User user = new User();
                user.setUsername(member.username());
                user.setEmail(member.username().replace('.', '_') + "@skillswap.example.test");
                user.setPassword(encoder.encode("DemoPass123!"));
                user = users.save(user);

                for (String name : member.offered()) {
                    skills.save(createSkill(user, name, UserSkillType.OFFERED, "Intermediate"));
                }
                for (String name : member.wanted()) {
                    skills.save(createSkill(user, name, UserSkillType.WANTED, "Beginner"));
                }
            }
        };
    }

    private Skill createSkill(User user, String name, UserSkillType type, String level) {
        Skill skill = new Skill();
        skill.setUser(user);
        skill.setName(name);
        skill.setType(type);
        skill.setLevel(level);
        return skill;
    }
}
