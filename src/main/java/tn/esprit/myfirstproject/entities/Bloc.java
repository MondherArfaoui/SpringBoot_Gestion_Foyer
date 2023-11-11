package tn.esprit.myfirstproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idBloc;

    String nomBloc;
    Long capaciteBloc;


    @ManyToOne
    @JoinColumn(name = "idFoyer")
    @JsonIgnore
    Foyer foyer;

    @OneToMany(mappedBy = "bloc", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Chambre> chambres;

}
