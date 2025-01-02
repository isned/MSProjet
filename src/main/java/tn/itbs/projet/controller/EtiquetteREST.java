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

import tn.itbs.projet.entities.Etiquette;
import tn.itbs.projet.service.EtiquetteService;



@RestController
@RequestMapping("/etiquette")
public class EtiquetteREST {
	
	@Autowired
	private EtiquetteService etiquetteService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> createEtiquette(@RequestBody Etiquette etiq) {
	    return etiquetteService.addEtiquette(etiq);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateEtiquette(@PathVariable int id, @RequestBody Etiquette etiq)
	{
		return etiquetteService.updateEtiquette(id,etiq);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteEtiquette(@PathVariable int id)
	{
		etiquetteService.deleteEtiquette(id);
	}
	
	@GetMapping("/all")
	public List<Etiquette> findAllEtiquettes() {
	    return etiquetteService.findListEtiquette();
	}
	
	@GetMapping("/get/{id}")
	public Optional<Etiquette> findProjet(@PathVariable int id) {
		return etiquetteService.findEtiquette(id);
	}

}
