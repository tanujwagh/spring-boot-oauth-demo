package com.sample.oauthserver.setup;

import com.sample.oauthserver.data.model.User;
import com.sample.oauthserver.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by macintosh on 7/28/17.
 */
@Configuration
public class UserDataSetup implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataSetup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User("user", passwordEncoder.encode("user"), true, "USER");
        User admin = new User("admin", passwordEncoder.encode("admin"), true, "USER, ADMIN");
        if(!userRepository.findByUsernameEquals(user.getUsername()).isPresent()){
            userRepository.save(user);
        }
        if(!userRepository.findByUsernameEquals(admin.getUsername()).isPresent()){
            userRepository.save(admin);
        }
    }
}
