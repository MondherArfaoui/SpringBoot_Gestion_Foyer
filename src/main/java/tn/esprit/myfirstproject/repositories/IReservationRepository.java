package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Reservation;

public interface IReservationRepository extends JpaRepository<Reservation, Long>  {
}
