package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.AuthenticationResponse;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.RefreshTokenRequest;

public interface IAuthenticationServices {
    Etudiant registerEtudiant(Etudiant etudiant);
    AuthenticationResponse login(String email, String password);
    AuthenticationResponse refreshToken(RefreshTokenRequest refreshToken);
}
