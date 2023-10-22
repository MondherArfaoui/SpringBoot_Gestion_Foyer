package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Bloc;

import java.util.List;

public interface IBlocServices {
    Bloc addBloc(Bloc bloc);
    Bloc updateBloc(Bloc bloc);
    List<Bloc> getAllBlocs();
    Bloc getBlocById(Long idBloc);
    void deleteBloc(Long idBloc);
}
