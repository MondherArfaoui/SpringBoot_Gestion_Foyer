package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Foyer;

import java.util.List;

public interface IFoyerServices {
    Foyer addFoyer(Foyer foyer);
    Foyer updateFoyer(Foyer foyer);
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long idFoyer);
    void deleteFoyer(Long idFoyer);
}
