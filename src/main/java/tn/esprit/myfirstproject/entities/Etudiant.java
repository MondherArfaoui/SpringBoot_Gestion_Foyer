package tn.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idEtudiant;

    String nomEt;
    String prenomEt;
    Long cin;
    String ecole;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date dateNaissance;


    @ManyToMany(mappedBy="etudiants")
    Set<Reservation> reservations;

}
