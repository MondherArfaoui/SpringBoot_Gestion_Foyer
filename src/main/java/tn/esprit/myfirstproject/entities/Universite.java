package tn.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idUniversite;

    String nomUniversite;
    String adresse;


    @OneToOne
    @JoinColumn(name = "idFoyer")
    Foyer foyer;

}
