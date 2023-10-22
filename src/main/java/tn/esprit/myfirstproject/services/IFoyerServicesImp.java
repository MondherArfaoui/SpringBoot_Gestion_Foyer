package tn.esprit.myfirstproject.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.repositories.IFoyerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IFoyerServicesImp implements IFoyerServices {

    private final IFoyerRepository foyerRepository;


    @Override
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public Foyer updateFoyer(Foyer foyer) {
        if (foyer.getIdFoyer() != null) {
            Foyer existingFoyer = foyerRepository.findById(foyer.getIdFoyer()).orElse(null);
            if (existingFoyer != null) {
                if (foyer.getNomFoyer() != null) {
                    existingFoyer.setNomFoyer(foyer.getNomFoyer());
                }
                if (foyer.getCapaciteFoyer() != null) {
                    existingFoyer.setCapaciteFoyer(foyer.getCapaciteFoyer());
                }
                if (foyer.getUniversite() != null) {
                    existingFoyer.setUniversite(foyer.getUniversite());
                }
                if (foyer.getBlocs() != null) {
                    existingFoyer.setBlocs(foyer.getBlocs());
                }
                return foyerRepository.save(existingFoyer);
            }
        }
        return null;
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer getFoyerById(Long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void deleteFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}
