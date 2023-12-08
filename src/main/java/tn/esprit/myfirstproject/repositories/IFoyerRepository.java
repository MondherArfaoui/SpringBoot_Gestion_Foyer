package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Foyer;

import java.util.List;

public interface IFoyerRepository extends JpaRepository<Foyer, Long> {
    List<Foyer> findByUniversiteIsNull();
}
