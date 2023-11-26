package tn.esprit.myfirstproject.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.myfirstproject.entities.Etudiant;

import java.io.IOException;
import java.util.List;

public interface IEtudiantServices {
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant updateEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long idEtudiant);
    void deleteEtudiant(Long idEtudiant);

    public Etudiant updatePassword (Long idEtudiant, String  password);
    public Etudiant updateImage (Long idEtudiant, MultipartFile file) throws IOException;
}
