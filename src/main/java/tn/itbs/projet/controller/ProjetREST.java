package tn.itbs.projet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.itbs.projet.entities.Projet;
import tn.itbs.projet.service.ProjetService;



@RestController
@RequestMapping("/projet")
public class ProjetREST {
	
	@Autowired
	private ProjetService projetService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> createProjet(@RequestBody Projet projet) {
	    return projetService.addProjet(projet);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProjet(@PathVariable int id, @RequestBody Projet projet)
	{
		return projetService.updateProjet(id,projet);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteProjet(@PathVariable int id)
	{
		projetService.deleteProjet(id);
	}
	
	@GetMapping("/all")
	public List<Projet> findAllContrats() {
	    return projetService.findListProjet();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Projet> findProjet(@PathVariable int id) {
		return projetService.findProjet(id);
	}
	
}

