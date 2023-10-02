package tn.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reservation implements Serializable {

    @Id
    private String idReservation;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date anneeUniversiaire;

    private boolean estValide;

    @ManyToMany
    private Set<Etudiant> etudiants;

}
