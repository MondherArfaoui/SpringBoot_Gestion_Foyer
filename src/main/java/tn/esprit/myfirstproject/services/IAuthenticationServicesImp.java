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

        if (user.getRole() == Role.ETUDIANT) {
            Etudiant etudiant = (Etudiant) user;
            Etudiant etudiantDto = convertToEtudiantDto(etudiant);
            authenticationResponse.setUserDetails(etudiantDto);
        } else {
            User userDetails = convertToUserDto(user);
            authenticationResponse.setUserDetails(userDetails);
        }

        return authenticationResponse;
    }

    private User convertToUserDto(User user) {
        User dto = new User();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setImage(user.getImage());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        return dto;
    }
    private Etudiant convertToEtudiantDto(Etudiant etudiant) {
        Etudiant dto = new Etudiant();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setImage(etudiant.getImage());
        dto.setEmail(etudiant.getEmail());
        dto.setPassword(etudiant.getPassword());
        dto.setRole(etudiant.getRole());
        dto.setCin(etudiant.getCin());
        dto.setEcole(etudiant.getEcole());
        dto.setDateNaissance(etudiant.getDateNaissance());
        return dto;
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
