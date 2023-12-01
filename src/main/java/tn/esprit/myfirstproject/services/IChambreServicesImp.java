package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.entities.TypeChambre;
import tn.esprit.myfirstproject.repositories.IChambreRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IChambreServicesImp implements IChambreServices {

    private final IChambreRepository chambreRepository;


    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        if (chambre.getIdChambre() != null) {
            Chambre existingChambre = chambreRepository.findById(chambre.getIdChambre()).orElse(null);
            if (existingChambre != null) {
                if (chambre.getNumeroChambre() != null) {
                    existingChambre.setNumeroChambre(chambre.getNumeroChambre());
                }
                if (chambre.getTypeC() != null) {
                    existingChambre.setTypeC(chambre.getTypeC());
                }
                if (chambre.getBloc() != null) {
                    existingChambre.setBloc(chambre.getBloc());
                }
                if (chambre.getReservations() != null) {
                    existingChambre.setReservations(chambre.getReservations());
                }
                return chambreRepository.save(existingChambre);
            }
        }
        return null;
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre getChambreById(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC) {
        return chambreRepository.getChambresParBlocEtType(idBloc, typeC);  //Solution 1
    }

    @Override
    public Long getNombreChambresParBloc(Long idBloc) {
        return chambreRepository.countByBloc(idBloc);
    }

}
