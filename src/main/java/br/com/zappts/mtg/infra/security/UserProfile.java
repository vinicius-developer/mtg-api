package br.com.zappts.mtg.infra.security;

import org.springframework.security.core.GrantedAuthority;

public class UserProfile implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
}
