package tn.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="nomUniversite", unique=true)
    String nomUniversite;

    String adresse;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idFoyer")
    @JsonIgnore
    Foyer foyer;

}
