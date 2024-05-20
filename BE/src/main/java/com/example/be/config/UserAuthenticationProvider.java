package com.example.be.config;


import com.example.be.Api.ApiException;
import com.example.be.dto.UserDto;
import com.example.be.models.User;
import com.example.be.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@ComponentScan("com.example.be")

public class UserAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private  final UserRepo userRepo;




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<User> user = Optional.of(userRepo.
                findUserByUsernameOrEmail(String.valueOf(authentication.getPrincipal()),
                        String.valueOf(authentication.getPrincipal()))

                .orElseThrow(() -> new ApiException("password or username isn't correct _______")));




        if (!passwordEncoder.matches(String.valueOf(authentication.getCredentials()), user.get().getPassword())) {

            throw new ApiException("password or username isn't correct ########");

        } else {

            UserDto userDto = new UserDto(user.get().getId()
                    ,user.get().getEmail()
                    ,user.get().getFirstname()
                    ,user.get().getLastname()
                    ,user.get().getRole().name()
                    ,null);

            return new JwtCustomerAuthenticationToken(userDto, user.get().getUsername(), "<Confidential>", getUserAuthorities(user.get().getId()));
        }

    }



    public Collection<GrantedAuthority> getUserAuthorities(Integer userId) {

        return userRepo
                .findUserById(userId)
                .map(user -> Collections.singletonList(user.getRole()))
                .orElse(Collections.emptyList())
                .stream()
                .map(role ->  new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtCustomerAuthenticationToken.class);
    }
}
