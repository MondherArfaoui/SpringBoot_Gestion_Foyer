package tn.esprit.myfirstproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.myfirstproject.entities.Admin;

public interface IAdminRepository extends JpaRepository<Admin, Long>  {
}
