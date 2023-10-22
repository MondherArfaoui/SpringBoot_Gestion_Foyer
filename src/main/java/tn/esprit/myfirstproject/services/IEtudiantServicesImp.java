package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.Foyer;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IEtudiantServicesImp implements IEtudiantServices {

    private final IEtudiantRepository etudiantRepository;


    @Override
    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
        if (etudiant.getIdEtudiant() != null) {
            Etudiant existingEtudiant = etudiantRepository.findById(etudiant.getIdEtudiant()).orElse(null);
            if (existingEtudiant != null) {
                if (etudiant.getNomEt() != null) {
                    existingEtudiant.setNomEt(etudiant.getNomEt());
                }
                if (etudiant.getPrenomEt() != null) {
                    existingEtudiant.setPrenomEt(etudiant.getPrenomEt());
                }
                if (etudiant.getCin() != null) {
                    existingEtudiant.setCin(etudiant.getCin());
                }
                if (etudiant.getDateNaissance() != null) {
                    existingEtudiant.setDateNaissance(etudiant.getDateNaissance());
                }
                if (etudiant.getEcole() != null) {
                    existingEtudiant.setEcole(etudiant.getEcole());
                }
                if (etudiant.getReservations() != null) {
                    existingEtudiant.setReservations(etudiant.getReservations());
                }
                return etudiantRepository.save(existingEtudiant);
            }
        }
        return null;
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiantById(Long idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void deleteEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }
}
