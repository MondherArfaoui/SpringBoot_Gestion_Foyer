package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Bloc;

import java.util.List;

public interface IBlocServices {
    Bloc addBloc(Bloc bloc);
    Bloc updateBloc(Bloc bloc);
    List<Bloc> getAllBlocs();
    List<Bloc> getAllBlocsByIdFoyer( Long idFoyer);
    Bloc getBlocById(Long idBloc);
    void deleteBloc(Long idBloc);

    Bloc affecterChambresABloc(List<Long> idChambre, Long idBloc);
    Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer) ;
}
