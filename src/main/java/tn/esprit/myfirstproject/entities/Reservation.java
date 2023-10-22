package tn.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
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

    @JsonFormat(pattern="dd-MM-yyyy")
    Date anneeUniversiaire;

    boolean estValide;


    @ManyToMany
    Set<Etudiant> etudiants;

}
