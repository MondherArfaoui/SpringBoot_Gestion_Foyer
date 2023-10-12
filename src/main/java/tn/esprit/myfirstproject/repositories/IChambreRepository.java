package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Chambre;

public interface IChambreRepository extends JpaRepository<Chambre, Long>  {
}
