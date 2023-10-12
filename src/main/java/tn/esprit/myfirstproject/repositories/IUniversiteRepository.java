package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Universite;

public interface IUniversiteRepository extends JpaRepository<Universite, Long>  {
}
