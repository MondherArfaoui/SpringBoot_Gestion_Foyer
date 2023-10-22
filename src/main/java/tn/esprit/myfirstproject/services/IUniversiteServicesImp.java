package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.repositories.IUniversiteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IUniversiteServicesImp implements IUniversiteServices {

    private final IUniversiteRepository universiteRepository;


    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite updateUniversite(Universite universite) {
        if (universite.getIdUniversite() != null) {
            Universite existingUniversite = universiteRepository.findById(universite.getIdUniversite()).orElse(null);
            if (existingUniversite != null) {
                if (universite.getNomUniversite() != null) {
                    existingUniversite.setNomUniversite(universite.getNomUniversite());
                }
                if (universite.getAdresse() != null) {
                    existingUniversite.setAdresse(universite.getAdresse());
                }
                if (universite.getFoyer() != null) {
                    existingUniversite.setFoyer(universite.getFoyer());
                }
                return universiteRepository.save(existingUniversite);
            }
        }
        return null;
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public void deleteUniversite(Long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }
}
