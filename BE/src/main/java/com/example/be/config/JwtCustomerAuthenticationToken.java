package com.example.be.config;

import com.example.be.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@Getter
@Setter
public class JwtCustomerAuthenticationToken extends UsernamePasswordAuthenticationToken {


    private UserDto userDto;


    public JwtCustomerAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public JwtCustomerAuthenticationToken(Object principal, Object credentials ) {
        super(principal, credentials);
    }
    public JwtCustomerAuthenticationToken(UserDto userDto , Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.userDto = userDto;
    }

}
