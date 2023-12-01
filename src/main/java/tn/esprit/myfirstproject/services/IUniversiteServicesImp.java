package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.repositories.IFoyerRepository;
import tn.esprit.myfirstproject.repositories.IUniversiteRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IUniversiteServicesImp implements IUniversiteServices {

    private final IUniversiteRepository universiteRepository;
    private final IFoyerRepository foyerRepository;


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
    public Universite getUniversiteByIdEtudiant(Long idEtudiant) {
        return universiteRepository.getUniversiteByIdEtudiant(idEtudiant);
    }


    @Override
    public Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);

        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);

        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }


}
