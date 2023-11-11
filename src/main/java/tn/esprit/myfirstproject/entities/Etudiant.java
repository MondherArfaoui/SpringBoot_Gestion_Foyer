package tn.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Etudiant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idEtudiant;

    String nomEt;
    String prenomEt;

    @Column(unique=true)
    Long cin;

    String ecole;

    @Temporal(TemporalType.DATE)
    LocalDate dateNaissance;


    @ManyToMany(mappedBy="etudiants")
    @JsonIgnore
    Set<Reservation> reservations;

}
