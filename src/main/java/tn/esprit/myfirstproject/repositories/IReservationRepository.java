package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.entities.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, String>  {
}
