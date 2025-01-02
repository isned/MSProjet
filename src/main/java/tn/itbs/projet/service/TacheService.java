package tn.itbs.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.projet.entities.Projet;
import tn.itbs.projet.entities.Tache;
import tn.itbs.projet.repositories.EtiquetteRepository;
import tn.itbs.projet.repositories.ProjetRepository;
import tn.itbs.projet.repositories.TacheRepository;




@Service
public class TacheService {
	
	@Autowired
	private TacheRepository tacheRepo;
	
	@Autowired
	private EtiquetteRepository etiquetteRepo;
	
	@Autowired
	private ProjetRepository projetRepo;
	
	@Transactional
	public ResponseEntity<Object> addTache(Tache tache) {
		tacheRepo
			.findById(tache.getId())
			.ifPresentOrElse( 
				t -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache is already exist");
				}, 
				() -> {
					Projet proj = projetRepo
							.findById(tache.getProjet().getId())
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet not found"));
					
					tache.setProjet(proj);
					proj.getListTaches().add(tache);
					
					tache.setStatut(Tache.Statut.EN_ATTENTE);
					tacheRepo.save(tache);
				});
		
		return ResponseEntity.ok().body("Ajout avec succès");
	}
	
	@Transactional
	public ResponseEntity<Object> updateTache(int id, Tache newTache)
	{
		tacheRepo
		.findById(id)
		.ifPresentOrElse( tache -> {
			if(tache.getStatut().equals(Tache.Statut.EN_ATTENTE)) {
				tache.setTitre(newTache.getTitre());
				tache.setDescription(newTache.getDescription());
				tache.setDateEcheance(newTache.getDateEcheance());
				tache.setStatut(newTache.getStatut());

				Projet proj = projetRepo
						.findById(tache.getProjet().getId())
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet not found"));
				
				tache.setProjet(proj);
	            
	            tacheRepo.save(tache);
			}else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Etat Demande Congé is not EN_ATTENTE");
			}
			
		}, 
		() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Demande Congé to update not found");
		});
		
		return ResponseEntity.ok().body("Update tache avec succé");
	}
	
	@Transactional
	public ResponseEntity<Object> changeStatusTacheTermine(int id) {
		tacheRepo
			.findById(id)
			.ifPresentOrElse( tache -> {
						if(tache.getStatut().equals(Tache.Statut.TERMINEE)) {
							tache.setStatut(Tache.Statut.EN_COURS);
							tacheRepo.save(tache);
						}else {
							throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Status tache not found");
						}				
			}, () -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found");
			});
		return ResponseEntity.ok().body("Update tache status avec succé");
	}
	
	@Transactional
	public void deleteTache(int id)
	{
		tacheRepo
			.findById(id)
			.ifPresentOrElse(
				tache -> {
					tache.getListEtiquette().forEach(etiq -> etiquetteRepo.delete(etiq));
					tacheRepo.delete(tache);
				}
				, 
				() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache to delete not found");
				});
	}
	
	@Transactional
	public List<Tache> findListTache() {
	    return tacheRepo.findAll();
	}
	
	@Transactional
	public Optional<Tache> findTache(int id)
	{
		return tacheRepo.findById(id);
	}
	

}
