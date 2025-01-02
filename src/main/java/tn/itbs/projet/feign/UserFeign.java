package tn.itbs.projet.feign;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.itbs.projet.entities.Utilisateur;


@FeignClient(name="microservice-utilisateur", url="http://localhost:9091")
public interface UserFeign {
	
	
	@GetMapping(path = "/user/get/{id}")
	Optional<Utilisateur> findUtilisateur(@PathVariable("id") int id);

	
}