package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.entities.Reservation;
import tn.esprit.myfirstproject.repositories.IReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IReservationServicesImp implements IReservationServices {

    private final IReservationRepository reservationRepository;


    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Reservation existingReservation = reservationRepository.findById(reservation.getIdReservation()).orElse(null);
            if (existingReservation != null) {
                if (reservation.getAnneeUniversiaire() != null) {
                    existingReservation.setAnneeUniversiaire(reservation.getAnneeUniversiaire());
                }
                if (reservation.getEtudiants() != null) {
                    existingReservation.setEtudiants(reservation.getEtudiants());
                }
                return reservationRepository.save(existingReservation);
            }
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }

    @Override
    public void deleteReservation(Long idReservation) {
        reservationRepository.deleteById(idReservation);
    }
}
