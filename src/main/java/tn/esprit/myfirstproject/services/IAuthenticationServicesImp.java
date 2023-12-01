package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tn.esprit.myfirstproject.config.EmailService;
import tn.esprit.myfirstproject.entities.*;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;
import tn.esprit.myfirstproject.repositories.IUserRepository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class IAuthenticationServicesImp implements IAuthenticationServices {

    private final IUserRepository userRepository;
    private final IEtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final IJWTServices jwtServices;
    private final EmailService emailService;

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
        dto.setUniversite(etudiant.getUniversite());
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

    @Override
    public HashMap<String, String> forgetPassword(String email) {
        HashMap message = new HashMap();

        User userexisting = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        UUID token = UUID.randomUUID();
        userexisting.setPasswordResetToken(token.toString());
        userexisting.setId(userexisting.getId());

        Mail mail = new Mail();

        mail.setSubject("Reset Password");
        mail.setTo(userexisting.getEmail());
        mail.setContent("Votre nouveau TOKEN est : " + "http://localhost:4200/resetpassword/"+userexisting.getPasswordResetToken());
        emailService.sendSimpleEmail(mail);
        userRepository.save(userexisting);
        message.put("user","user FOUND and email is Sent");
        return message;
    }

    @Override
    public HashMap<String,String> resetPassword(@PathVariable String passwordResetToken, String newPassword){
        User userexisting = userRepository.findByPasswordResetToken(passwordResetToken).orElseThrow(() -> new RuntimeException("User not found"));
        HashMap message = new HashMap();
        if (userexisting != null) {
            userexisting.setId(userexisting.getId());
            userexisting.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userexisting.setPasswordResetToken(null);
            userRepository.save(userexisting);
            message.put("resetpassword","succès");
            return message;
        }else
        {
            message.put("resetpassword","Échoué ");
            return message;
        }
    }
}
