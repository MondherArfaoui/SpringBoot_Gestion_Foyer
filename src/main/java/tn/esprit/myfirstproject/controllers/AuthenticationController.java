package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.myfirstproject.entities.AuthenticationResponse;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.RefreshTokenRequest;
import tn.esprit.myfirstproject.entities.User;
import tn.esprit.myfirstproject.services.IAuthenticationServices;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final IAuthenticationServices authenticationServices;

  @PostMapping("/registerEtudiant")
  public Etudiant registerEtudiant(@RequestBody Etudiant etudiant) {
    return authenticationServices.registerEtudiant(etudiant);
  }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User user) {
        return authenticationServices.login(user.getEmail(), user.getPassword());
    }

    @PostMapping("/refreshToken")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        return authenticationServices.refreshToken(refreshToken);
    }
}
