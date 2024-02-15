package com.jedco.jedcoengineeringspring.services;
import com.jedco.jedcoengineeringspring.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(User userDetails);

    String generateRefreshToken(User userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    long expirationTime(String token);

    boolean isTokenExpired(long expirationTime);

}
