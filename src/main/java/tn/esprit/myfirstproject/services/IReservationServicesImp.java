package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.myfirstproject.entities.Chambre;
import tn.esprit.myfirstproject.entities.Etudiant;
import tn.esprit.myfirstproject.entities.Reservation;
import tn.esprit.myfirstproject.entities.TypeChambre;
import tn.esprit.myfirstproject.repositories.IChambreRepository;
import tn.esprit.myfirstproject.repositories.IEtudiantRepository;
import tn.esprit.myfirstproject.repositories.IReservationRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class IReservationServicesImp implements IReservationServices {

    private final IReservationRepository reservationRepository;
    private final IEtudiantRepository etudiantRepository;
    private final IChambreRepository chambreRepository;



    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
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
    public Reservation getCurrentReservationByEtudiantId(Long idEtudiant) {
        LocalDate currentDate = LocalDate.now();
        return reservationRepository.getCurrentReservationByEtudiantId(idEtudiant, currentDate);
    }


    @Override
    public Reservation ajouterReservation(Long idChambre, Long cin) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Etudiant etudiant = etudiantRepository.findByCin(cin);

        // Assert that the chambre and etudiant are not null
        assert chambre != null;
        assert etudiant != null;

        // Determine the current academic year
        LocalDate debutAnnee = LocalDate.of(LocalDate.now().getYear(), 9, 1);
        LocalDate finAnnee = LocalDate.of(LocalDate.now().getYear() + 1, 6, 1);

        // Check if the etudiant already has a reservation for the current academic year
        boolean hasExistingReservation = etudiant.getReservations().stream()
                .anyMatch(reservation -> reservation.getDebutAnneeUniv().equals(debutAnnee) && reservation.getFinAnneeUniv().equals(finAnnee));

        if (hasExistingReservation) {
            throw new IllegalStateException("L'étudiant a déjà une réservation pour l'année académique en cours.");
        }

        // Check the maximum capacity of the chambre
        int capaciteMax = switch (chambre.getTypeC()) {
            case SIMPLE -> 1;
            case DOUBLE -> 2;
            case TRIPLE -> 3;
        };

        // Verify if the maximum capacity of the chambre is reached
        long nombreReservations = chambre.getReservations().size();
        if (nombreReservations >= capaciteMax) {
            throw new IllegalStateException("La capacité maximale de la chambre est atteinte.");
        }

        // Create the reservation
        Reservation reservation = new Reservation();
        reservation.setNumReservation(chambre.getNumeroChambre() + "-" + chambre.getBloc().getNomBloc().replace(" ", "") + "-" + cin);
        reservation.setDebutAnneeUniv(debutAnnee);
        reservation.setFinAnneeUniv(finAnnee);
        reservation.setEstValide(true);

        // Handle the ManyToMany relationship
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // Save the reservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Add the reservation to the chambre and save
        chambre.getReservations().add(savedReservation);
        chambreRepository.save(chambre);

        return savedReservation;
    }


    @Override
    @Transactional
    public Reservation annulerReservation(Long cinEtudiant) {
        // Trouver l'étudiant et sa réservation
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);

        // Supposition: chaque étudiant a au maximum une réservation valide
        Reservation reservation = etudiant.getReservations().stream()
                .filter(Reservation::isEstValide)
                .findFirst()
                .orElse(null);

        // Mettre à jour l'état de la réservation
        reservation.setEstValide(false);

        // Désaffecter l'étudiant
        reservation.getEtudiants().remove(etudiant);

        // Désaffecter la chambre associée et mettre à jour sa capacité
        Chambre chambreAssociee = chambreRepository.findByReservationsContains(reservation);
        if (chambreAssociee != null) {
            chambreAssociee.getReservations().remove(reservation);
            chambreRepository.save(chambreAssociee); // Sauvegarder les changements dans la chambre
        }

        // Sauvegarder les modifications
        return reservationRepository.save(reservation);
    }


    @Transactional
    @Scheduled(cron = "0 0 0 2 6 *") // À minuit, le 2 juin de chaque année
    public void annulerToutesLesReservationsAutomatiquement() {
        List<Reservation> reservationsValides = reservationRepository.findAllByEstValide(true);

        for (Reservation reservation : reservationsValides) {
            reservation.setEstValide(false); // Marquer la réservation comme invalide

            // Désaffecter les étudiants de la réservation
            for (Etudiant etudiant : reservation.getEtudiants()) {
                etudiant.getReservations().remove(reservation);
            }
            reservation.getEtudiants().clear();

            // Désaffecter la chambre associée et mettre à jour sa capacité
            Chambre chambreAssociee = chambreRepository.findByReservationsContains(reservation);
            if (chambreAssociee != null) {
                chambreAssociee.getReservations().remove(reservation);
                chambreRepository.save(chambreAssociee);
            }

            // Sauvegarder la réservation modifiée
            reservationRepository.save(reservation);
        }
    }
}
