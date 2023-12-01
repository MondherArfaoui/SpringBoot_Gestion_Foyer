package tn.esprit.myfirstproject.entities;

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
public class Etudiant extends User {

    @Column(unique=true)
    Long cin;

    @Temporal(TemporalType.DATE)
    LocalDate dateNaissance;


    @ManyToMany(mappedBy="etudiants")
    @JsonIgnore
    Set<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "idUniversite")
    Universite universite;
}
