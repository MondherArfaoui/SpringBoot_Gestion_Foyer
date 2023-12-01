package tn.esprit.myfirstproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.myfirstproject.entities.Reservation;
import tn.esprit.myfirstproject.services.IReservationServices;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final IReservationServices reservationService;


    @PutMapping("/update")
    public Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{idReservation}")
    public Reservation getReservationById(@PathVariable Long idReservation) {
        return reservationService.getReservationById(idReservation);
    }

    @GetMapping("/getReservationsByEtudiantId/{idEtudiant}")
    public Reservation getReservationsByEtudiantId(@PathVariable Long idEtudiant) {
        return reservationService.getCurrentReservationByEtudiantId(idEtudiant);
    }


    @PostMapping("/add/{idChambre}/{cin}")
    public Reservation ajouterReservation(@PathVariable Long idChambre, @PathVariable Long cin) {
        return reservationService.ajouterReservation(idChambre, cin);
    }

    @PutMapping("/annulerReservation/{cin}")
    public Reservation annulerReservation(@PathVariable Long cin) {
        return reservationService.annulerReservation(cin);
    }
}
