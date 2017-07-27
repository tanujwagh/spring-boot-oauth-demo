package com.sample.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * Created by macintosh on 7/27/17.
 */
@Configuration
public class JWTConfiguration {

    private Environment environment;

    @Autowired
    public JWTConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        final String password = environment.getProperty("keystore.password");
        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource("jwt.jks"), password.toCharArray());
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
        return converter;
    }
}
