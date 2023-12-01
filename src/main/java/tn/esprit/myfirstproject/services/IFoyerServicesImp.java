package tn.esprit.myfirstproject.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.myfirstproject.entities.Bloc;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.entities.Universite;
import tn.esprit.myfirstproject.repositories.IBlocRepository;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;
import tn.esprit.myfirstproject.repositories.IFoyerRepository;
import tn.esprit.myfirstproject.repositories.IUniversiteRepository;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class IFoyerServicesImp implements IFoyerServices {

    private final IFoyerRepository foyerRepository;
    private final IUniversiteRepository universiteRepository;
    private final IBlocRepository blocRepository;
    private final IEtudiantRepository etudiantRepository;


    @Override
    public Foyer ajouterFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    @Transactional
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        universite.setFoyer(foyer);

        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(foyer);
            blocRepository.save(bloc);
        }

        return foyer;
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
                if (foyer.getBlocs() != null) {
                    existingFoyer.setBlocs(foyer.getBlocs());
                }
                if (foyer.getUniversite() != null) {
                    existingFoyer.setUniversite(foyer.getUniversite());
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
    public Foyer getFoyerByIdEtudiant(Long idEtudiant) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);

        Universite universite = etudiant.getUniversite();

        Foyer foyer = universite.getFoyer();

        return foyer;
    }

    @Override
    public void deleteFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}
