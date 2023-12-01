package tn.esprit.myfirstproject.services;

import tn.esprit.myfirstproject.entities.Reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IReservationServices {
    Reservation updateReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long idReservation);

    Reservation getCurrentReservationByEtudiantId(Long idEtudiant);

    Reservation ajouterReservation (Long idChambre, Long cinEtudiant) ;
    Reservation annulerReservation (Long cinEtudiant) ;
}
