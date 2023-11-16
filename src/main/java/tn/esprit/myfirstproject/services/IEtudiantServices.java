package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Etudiant;

import java.util.List;

public interface IEtudiantServices {
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant updateEtudiant(Etudiant etudiant);
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long idEtudiant);
    void deleteEtudiant(Long idEtudiant);
}
