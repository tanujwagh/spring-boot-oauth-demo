package com.sample.oauthserver.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by macintosh on 7/28/17.
 */
@RestController
public class SecurityController {

    @RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Principal getPrincipal(final Principal principal){
        return principal;
    }
}
