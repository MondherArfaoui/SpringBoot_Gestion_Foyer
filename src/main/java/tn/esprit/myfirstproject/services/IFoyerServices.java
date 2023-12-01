package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Foyer;

import java.util.List;

public interface IFoyerServices {

    Foyer ajouterFoyer(Foyer foyer);
    Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, Long idUniversite) ;
    Foyer updateFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long idFoyer);

    Foyer getFoyerByIdEtudiant(Long idEtudiant);

    void deleteFoyer(Long idFoyer);
}
