package tn.itbs.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.projet.entities.Tache;


@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{

}
