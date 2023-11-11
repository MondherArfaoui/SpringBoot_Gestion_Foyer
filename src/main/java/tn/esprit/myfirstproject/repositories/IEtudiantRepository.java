package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Etudiant;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long>  {
    Etudiant findByCin(Long cin);
}
