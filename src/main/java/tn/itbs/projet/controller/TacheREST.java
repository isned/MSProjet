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

import tn.itbs.projet.entities.Tache;
import tn.itbs.projet.service.TacheService;



@RestController
@RequestMapping("/tache")
public class TacheREST {
	
	@Autowired
	private TacheService tacheService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> createTache(@RequestBody Tache tache) {
	    return tacheService.addTache(tache);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateTache(@PathVariable int id, @RequestBody Tache tache)
	{
		return tacheService.updateTache(id, tache);
	}
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<Object> updateTacheStatus(@PathVariable int id)
	{
		return tacheService.changeStatusTacheTermine(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteTache(@PathVariable int id)
	{
		tacheService.deleteTache(id);
	}
	
	@GetMapping("/all")
	public List<Tache> findAllTaches() {
	    return tacheService.findListTache();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Tache> findTache(@PathVariable int id) {
		return tacheService.findTache(id);
	}

}
