package tn.esprit.myfirstproject.services;

import org.springframework.security.core.userdetails.UserDetails;
import tn.esprit.myfirstproject.entities.User;

import java.util.HashSet;
import java.util.Map;

public interface IJWTServices {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);
}
