package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.myfirstproject.entities.Universite;

public interface IUniversiteRepository extends JpaRepository<Universite, Long>  {
    Universite findByNomUniversite(String nomUniversite);

    @Query("SELECT u.universite FROM Etudiant u WHERE u.id = :idEtudiant")
    Universite getUniversiteByIdEtudiant(Long idEtudiant);
}
