package com.sample.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by macintosh on 7/27/17.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration  extends AuthorizationServerConfigurerAdapter{

    private AuthenticationManager authenticationManager;
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    private UserDetailsService userDetailsService;

    @Autowired
    public OAuth2ServerConfiguration(@Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager, JwtAccessTokenConverter jwtAccessTokenConverter, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("demo-app")
                .secret("demo-secret")
                .accessTokenValiditySeconds(60)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter)
        .authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }


}
