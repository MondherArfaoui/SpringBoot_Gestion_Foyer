package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Bloc;

public interface IBlocRepository extends JpaRepository<Bloc, Long> {
}
