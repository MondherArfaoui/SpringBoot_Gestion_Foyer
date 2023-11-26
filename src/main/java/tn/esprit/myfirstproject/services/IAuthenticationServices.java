package tn.esprit.myfirstproject.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.myfirstproject.entities.AuthenticationResponse;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.RefreshTokenRequest;

import java.util.HashMap;

public interface IAuthenticationServices {
    Etudiant registerEtudiant(Etudiant etudiant);
    AuthenticationResponse login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
    HashMap<String,String> forgetPassword(String email);
    HashMap<String,String> resetPassword(String passwordResetToken, String newPassword);
}
