package com.sample.oauthserver.data.repository;

import com.sample.oauthserver.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by macintosh on 7/28/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameEquals(String username);
}
