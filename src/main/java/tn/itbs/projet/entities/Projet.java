package tn.itbs.projet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
@Entity
public class Projet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomProjet;
	private String description;
	private Date dateDebut;
	private Date dateFin;
	
	@Transient
	private Utilisateur user;
	private int idUser;
	
	//@ManyToOne
	//private Utilisateur user;
	
	@OneToMany(mappedBy = "projet")
	private List<Tache> ListTaches = new ArrayList<>();
}
