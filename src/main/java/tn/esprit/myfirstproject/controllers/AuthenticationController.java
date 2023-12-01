package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.myfirstproject.entities.*;
import tn.esprit.myfirstproject.services.IAuthenticationServices;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  public static String uploadDirectory = System.getProperty("user.dir") + "/uploadUser";

  private final IAuthenticationServices authenticationServices;

  @PostMapping("/registerEtudiant")
  public ResponseEntity<Etudiant> registerEtudiant(@RequestParam("nom") String nom,
                                                   @RequestParam("prenom") String prenom,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("cin") Long cin,
                                                   @RequestParam("universite") Universite universite,
                                                   @RequestParam("dateNaissance") LocalDate dateNaissance,
                                                   @RequestParam("image") MultipartFile file) throws IOException {
    Etudiant etudiant = new Etudiant();
    etudiant.setNom(nom);
    etudiant.setPrenom(prenom);
    etudiant.setEmail(email);
    etudiant.setPassword(password);
    etudiant.setCin(cin);
    etudiant.setUniversite(universite);
    etudiant.setDateNaissance(dateNaissance);

    String originalFilename = file.getOriginalFilename();
    String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;
    Path fileNameAndPath = Paths.get(uploadDirectory, uniqueFilename);

    if (!Files.exists(fileNameAndPath.getParent())) {
      Files.createDirectories(fileNameAndPath.getParent());
    }

    Files.write(fileNameAndPath, file.getBytes());
    etudiant.setImage(uniqueFilename);

    Etudiant savedEtudiant = authenticationServices.registerEtudiant(etudiant);
    return ResponseEntity.ok(savedEtudiant);
  }

  @GetMapping("/{filename:.+}")
  @ResponseBody
  public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException {

    Path filePath = Paths.get(uploadDirectory).resolve(filename);
    Resource file = new UrlResource(filePath.toUri());

    if (file.exists() || file.isReadable()) {
      return ResponseEntity
              .ok()
              .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
              .body(file);
    } else {
      throw new RuntimeException("Could not read the file!");
    }
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody User user) {
      return authenticationServices.login(user.getEmail(), user.getPassword());
  }

  @PostMapping("/refreshToken")
  public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
      return authenticationServices.refreshToken(refreshToken);
  }

  @PostMapping("/forgetpassword")
  public HashMap<String,String> forgetPassword(@RequestParam String email){
        return authenticationServices.forgetPassword(email);
  }

    @PostMapping("/resetPassword/{passwordResetToken}")
    public HashMap<String,String> resetPassword(@PathVariable String passwordResetToken, String newPassword){
        return authenticationServices.resetPassword(passwordResetToken, newPassword);
    }
}
