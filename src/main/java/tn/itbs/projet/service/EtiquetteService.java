package tn.itbs.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.projet.entities.Etiquette;
import tn.itbs.projet.entities.Tache;
import tn.itbs.projet.repositories.EtiquetteRepository;
import tn.itbs.projet.repositories.TacheRepository;




@Service
public class EtiquetteService {
	
	@Autowired
	private EtiquetteRepository etiquetteRepo;
	
	@Autowired
	private TacheRepository tacheRepo;
	
	@Transactional
	public ResponseEntity<Object> addEtiquette(Etiquette etiquette) {
		etiquetteRepo
			.findById(etiquette.getId())
			.ifPresentOrElse( 
				e -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiquette is already exist");
				}, 
				() -> {
					Tache tache = tacheRepo
							.findById(etiquette.getTache().getId())
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found"));
					
					etiquette.setTache(tache);
					tache.getListEtiquette().add(etiquette);
					etiquetteRepo.save(etiquette);
				}
			);
		
		return ResponseEntity.ok().body("Ajout avec succès");
	}
	
	
	@Transactional
	public ResponseEntity<Object> updateEtiquette(int id, Etiquette newEtiquette)
	{
		etiquetteRepo
			.findById(id)
			.ifPresentOrElse(
			etiq -> {
				etiq.setLibelle(newEtiquette.getLibelle());
				
				Tache tache = tacheRepo
						.findById(etiq.getTache().getId())
						.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tache not found"));
				
				etiq.setTache(tache);
				etiquetteRepo.save(etiq);
		}, 
		() ->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiquette to update not found");
		});
		return ResponseEntity.ok().body("Update avec succès");
	}
	
	@Transactional
	public void deleteEtiquette(int id)
	{
		etiquetteRepo
			.findById(id)
			.ifPresentOrElse(
				etiq -> {
					etiquetteRepo.delete(etiq);
				}
				, 
				() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Etiquette to delete not found");
				});
	}
	
	@Transactional
	public List<Etiquette> findListEtiquette() {
	    return etiquetteRepo.findAll();
	}
	
	@Transactional
	public Optional<Etiquette> findEtiquette(int id)
	{
		return etiquetteRepo.findById(id);
	}

}
