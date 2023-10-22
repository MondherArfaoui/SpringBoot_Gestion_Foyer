package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Chambre;

import java.util.List;

public interface IChambreServices {
    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long idChambre);
    void deleteChambre(Long idChambre);
}
