package com.sample.oauthserver.config;

import com.sample.oauthserver.data.model.User;
import com.sample.oauthserver.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by macintosh on 7/28/17.
 */
@Component("userDetailsService")
public class OAuth2UserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public OAuth2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsernameEquals(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
