package tn.itbs.projet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tn.itbs.projet.entities.Projet;
import tn.itbs.projet.entities.Utilisateur;
import tn.itbs.projet.feign.UserFeign;
import tn.itbs.projet.repositories.ProjetRepository;
import tn.itbs.projet.repositories.TacheRepository;



@Service
public class ProjetService {

	@Autowired
	private ProjetRepository projetRepo;
	
	@Autowired
	private UserFeign userFeign;
	
	@Autowired
	private TacheRepository tacheRepo;
	
	
	
	public ResponseEntity<Object> addProjet(Projet projet) {
		projetRepo
			.findById(projet.getId())
			.ifPresentOrElse( 
				p -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet is already exist");
				}, 
				() -> {
					Utilisateur user = userFeign.findUtilisateur(projet.getIdUser()).get();
					if(user != null) {
						projet.setUser(user);
						projetRepo.save(projet);
					}else {
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
					}
				});
		
		return ResponseEntity.ok().body("Ajout avec succès");
	}
	
	
	public ResponseEntity<Object> updateProjet(int id, Projet newProj)
	{
		projetRepo
			.findById(id)
			.ifPresentOrElse(
			proj -> {
				proj.setNomProjet(newProj.getNomProjet());
				proj.setDescription(newProj.getDescription());
				proj.setDateDebut(newProj.getDateDebut());
				proj.setDateFin(newProj.getDateFin());
				
				Utilisateur user = userFeign.findUtilisateur(newProj.getIdUser()).get();
				if(user != null) {
					proj.setIdUser(user.getId());
					projetRepo.save(proj);
		        } else {
		            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		        }
		}, 
		() ->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet to update not found");
		});
		return ResponseEntity.ok().body("Update avec succès");
	}
	
	public void deleteProjet(int id)
	{
		projetRepo
			.findById(id)
			.ifPresentOrElse(
				proj -> {
					proj.getListTaches().forEach(tach -> tacheRepo.delete(tach));
					projetRepo.delete(proj);
				}
				, 
				() ->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Projet to delete not found");
				});
	}

	
	public List<Projet> findListProjet() {
	    return projetRepo.findAll();
	}
	
	public Optional<Projet> findProjet(int id)
	{
		Optional<Projet> pro = projetRepo.findById(id);
		Utilisateur user = userFeign.findUtilisateur(pro.get().getIdUser()).get();
		pro.get().setUser(user);
		return pro;
	}
}
