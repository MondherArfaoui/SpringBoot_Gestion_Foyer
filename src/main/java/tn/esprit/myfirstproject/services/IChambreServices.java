package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.entities.TypeChambre;

import java.util.List;

public interface IChambreServices {
    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Chambre chambre);
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long idChambre);

    List<Chambre> getChambresParBlocEtType (Long idBloc, TypeChambre typeC) ;

    Long getNombreChambresParBloc(Long idBloc);
}
