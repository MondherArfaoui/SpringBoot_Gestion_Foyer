package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.TypeChambre;

import java.util.List;

public interface IEtudiantRepository extends JpaRepository<Etudiant, Long>  {
    Etudiant findByCin(Long cin);

    @Query("select distinct e.cin from Chambre ch join ch.reservations r join r.etudiants e where ch.typeC = :typeC")
    List<Long> cinEtudiantReservationByTypeC(@Param("typeC") TypeChambre typeC);

}
