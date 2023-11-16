package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.*;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;
import tn.esprit.myfirstproject.repositories.IUserRepository;

import java.util.HashMap;
import java.util.HashSet;

@Service
@Slf4j
@RequiredArgsConstructor
public class IAuthenticationServicesImp implements IAuthenticationServices {

    private final IUserRepository userRepository;
    private final IEtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJWTServices jwtServices;

    public Etudiant registerEtudiant(Etudiant etudiant) {
        etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
        etudiant.setRole(Role.ETUDIANT);
        return etudiantRepository.save(etudiant);
    }

    public AuthenticationResponse login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        var jwt = jwtServices.generateToken(user);
        var refreshToken = jwtServices.generateRefreshToken(new HashMap<>(), user);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        authenticationResponse.setAccessToken(jwt);
        authenticationResponse.setRefreshToken(refreshToken);
        return authenticationResponse;
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken) {
        String userEmail = jwtServices.extractUsername(refreshToken.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if(jwtServices.isTokenValid(refreshToken.getRefreshToken(), user)) {
            var jwt = jwtServices.generateToken(user);

            AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            authenticationResponse.setAccessToken(jwt);
            authenticationResponse.setRefreshToken(refreshToken.getRefreshToken());
            return authenticationResponse;
        }
        return null;
    }
}
