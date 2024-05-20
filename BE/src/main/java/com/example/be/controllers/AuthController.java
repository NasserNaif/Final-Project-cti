package com.example.be.controllers;


import com.example.be.Api.ApiResponse;
import com.example.be.config.JwtService;
import com.example.be.dto.LoginRequest;
import com.example.be.dto.RegisterRequest;
import com.example.be.dto.UserDto;
import com.example.be.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.status(200).body(new ApiResponse("register success"));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        return ResponseEntity.status(200).body(authService.login(request));
    }

    @GetMapping("/id")
    public ResponseEntity<Integer> getID() {
        return ResponseEntity.status(200).body(jwtService.getUserId());
    }
}
