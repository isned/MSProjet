package tn.itbs.projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.projet.entities.Etiquette;


@Repository
public interface EtiquetteRepository extends JpaRepository<Etiquette, Integer>{

}
