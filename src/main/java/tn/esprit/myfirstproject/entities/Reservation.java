package tn.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReservation;

    String numReservation;

    @Temporal(TemporalType.DATE)
    LocalDate debutAnneeUniv;

    @Temporal(TemporalType.DATE)
    LocalDate finAnneeUniv;

    boolean estValide;


    @ManyToMany
    Set<Etudiant> etudiants;

}
