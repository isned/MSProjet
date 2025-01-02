package tn.itbs.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.projet.entities.Projet;


@Repository
public interface ProjetRepository extends JpaRepository<Projet, Integer>{

}
