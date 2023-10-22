package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.repositories.IBlocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IBlocServicesImp implements IBlocServices {

    private final IBlocRepository blocRepository;


    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        if (bloc.getIdBloc() != null) {
            Bloc existingBloc = blocRepository.findById(bloc.getIdBloc()).orElse(null);
            if (existingBloc != null) {
                if (bloc.getNomBloc() != null) {
                    existingBloc.setNomBloc(bloc.getNomBloc());
                }
                if (bloc.getCapaciteBloc() != null) {
                    existingBloc.setCapaciteBloc(bloc.getCapaciteBloc());
                }
                if (bloc.getFoyer() != null) {
                    existingBloc.setFoyer(bloc.getFoyer());
                }
                if (bloc.getChambres() != null) {
                    existingBloc.setChambres(bloc.getChambres());
                }
                return blocRepository.save(existingBloc);
            }
        }
        return null;
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc getBlocById(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void deleteBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }
}
