package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.myfirstproject.entities.*;

import java.util.List;

public interface IChambreRepository extends JpaRepository<Chambre, Long>  {
    //Solution 1
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> getChambresParBlocEtType(Long idBloc, TypeChambre typeC);

    //Solution 2
    List<Chambre> findByBlocIdBlocAndTypeC(Long idBloc, TypeChambre typeC);

    Chambre findByReservationsContains(Reservation reservation);

    @Query("SELECT COUNT(c) FROM Chambre c WHERE c.bloc.idBloc = :idBloc")
    Long countByBloc(@Param("idBloc") Long idBloc);
}
