package tn.esprit.myfirstproject.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Reservation getReservationById(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }


    @Override
    public Reservation ajouterReservation (Long idChambre, Long cin)  {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);

        Etudiant etudiant = etudiantRepository.findByCin(cin);

        // Création de la réservation
        Reservation reservation = new Reservation();
        assert chambre != null;
        reservation.setNumReservation(chambre.getNumeroChambre() +"-"+ chambre.getBloc().getNomBloc().replace(" ", "") +"-"+ cin);
        reservation.setDebutAnneeUniv(LocalDate.of(LocalDate.now().getYear(), 9, 1));
        reservation.setFinAnneeUniv(LocalDate.of(LocalDate.now().getYear() + 1, 6, 1));
        reservation.setEstValide(true);

        // Déterminer la capacité maximale en fonction du type de chambre
        int capaciteMax = 0;
        if (TypeChambre.SIMPLE.equals(chambre.getTypeC())) {
            capaciteMax = 1;
        } else if (TypeChambre.DOUBLE.equals(chambre.getTypeC())) {
            capaciteMax = 2;
        } else if (TypeChambre.TRIPLE.equals(chambre.getTypeC())) {
            capaciteMax = 3;
        }

        // Vérifier si la capacité maximale de la chambre est atteinte
        long nombreReservations = chambre.getReservations().size();
        if (nombreReservations >= capaciteMax) {
            throw new IllegalStateException("La capacité maximale de la chambre est atteinte.");
        }

        // Gérer la relation ManyToMany
        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        // Sauvegarder la réservation
        Reservation savedReservation = reservationRepository.save(reservation);

        // Ajouter la réservation à la collection de réservations de la chambre et sauvegarder
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
}
