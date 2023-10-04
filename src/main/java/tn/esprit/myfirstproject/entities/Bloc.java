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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    Long idBloc;

    String nomBloc;
    Long capaciteBloc;


    @ManyToOne
    @JoinColumn(name = "idFoyer")
    Foyer foyer;

    @OneToMany(mappedBy = "bloc")
    Set<Chambre> chambres;

}
