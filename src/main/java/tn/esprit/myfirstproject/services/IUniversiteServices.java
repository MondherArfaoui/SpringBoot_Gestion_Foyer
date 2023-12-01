package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Universite;

import java.util.List;

public interface IUniversiteServices {
    Universite addUniversite(Universite universite);
    Universite updateUniversite(Universite universite);
    List<Universite> getAllUniversites();
    Universite getUniversiteById(Long idUniversite);

    Universite getUniversiteByIdEtudiant(Long idEtudiant);

    Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(Long idUniversite);
}
