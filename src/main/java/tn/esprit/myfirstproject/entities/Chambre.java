package tn.esprit.myfirstproject.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idChambre;

    Long numeroChambre;

    @Enumerated(EnumType.STRING)
    TypeChambre typeC;


    @ManyToOne
    @JoinColumn(name = "idBloc")
    Bloc bloc;

    @OneToMany
    Set<Reservation> reservations;
}
