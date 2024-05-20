package com.example.be.config;


import com.example.be.Api.ApiException;
import com.example.be.dto.UserDto;
import com.example.be.models.User;
import com.example.be.repos.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET_KEY = "li1y9bdnbrtwjMMV7EJxPElG6fWorcv2mJxwU0rI5ZX/NU0eKbQ60C/3c73+DZH6UP7AcOHX844qzIyFkbfGVVOhdl6o1k5gpg8HXG4Qx+DScuNEZMhjQx1UF/RDoTjec5FEPtaUhJLQenBVmPvKv6y5a60eFec+rG7a39aDAiQ8Ky9fFZwy8SMGw7cSeG1UR1UTXl/nRlTI9rxNE4ZjAnhD+VqwiuK8J8EUPBBdr3hEknblC3ZqEuG5Wls5UPmyPydf5gewxml1ziXm2khWcrKjEtRME6+X67wo6EFeX4zXAnA4AHKqqxoSWDK6GyABjF5FM2lIWoP6PyMqKLuuIKEcEiAeEBSfK68v6R0golk=";
    private final UserRepo userRepo;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String generateToken(
            Authentication userDetails
    ) {
        return Jwts.builder()
                .setClaims(generateClaims(userDetails))
                .setSubject(userDetails.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public JwtCustomerAuthenticationToken getAuthentication(final String token) {

        final Claims claims =  extractAllClaims(token);
        final Collection<? extends GrantedAuthority> authorities = getUserAuthorities(claims.get("userId",Integer.class));

        return new JwtCustomerAuthenticationToken(
                getUserDto(claims)
                ,claims.getSubject(),
                "<Confidential>", authorities);
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
    private Map<String, Object> generateClaims(Authentication authentication) {

        UserDto u = ((JwtCustomerAuthenticationToken) authentication).getUserDto();


        final Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("userId", u.getId());
        claimsMap.put("email", u.getEmail());
        claimsMap.put("role", getAuthorities(authentication));
        return claimsMap;
    }

    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())) && isTokenExpired(token);
    }

    private UserDto getUserDto(Claims claims) {
        Integer userId = claims.get("userId", Integer.class);

        Optional<User> user = Optional.ofNullable(userRepo
                .findUserById(userId)
                .orElseThrow(() -> new ApiException("password or username isn't correct _______")));


        return new UserDto(user.get().getId()
                ,user.get().getEmail()
                ,user.get().getFirstname()
                ,user.get().getLastname()
                ,user.get().getRole().name()
                ,null);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof JwtCustomerAuthenticationToken) {
            return ((JwtCustomerAuthenticationToken) authentication).getUserDto().getId();
        } else {
            // Handle the case where authentication is not of the expected type
            // For example, you can return null or throw an exception
            return null;
        }
    }
}
