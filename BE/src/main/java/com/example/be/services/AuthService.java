package com.example.be.services;


import com.example.be.config.JwtCustomerAuthenticationToken;
import com.example.be.config.JwtService;
import com.example.be.config.UserAuthenticationProvider;
import com.example.be.dto.LoginRequest;
import com.example.be.dto.RegisterRequest;
import com.example.be.dto.UserDto;
import com.example.be.models.Role;
import com.example.be.models.User;
import com.example.be.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserAuthenticationProvider userAuthenticationProvider;


    @Transactional
    public void register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepo.save(user);

    }


    public UserDto login(LoginRequest request) {

        Authentication authenticate = userAuthenticationProvider.authenticate(

                new JwtCustomerAuthenticationToken(request.getUsername(), request.getPassword())
        );



        UserDto userDto =  ((JwtCustomerAuthenticationToken) authenticate).getUserDto();
        String token = jwtService.generateToken(authenticate);

        userDto.setToken(token);

        return  userDto;

    }


}
