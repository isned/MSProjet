package tn.itbs.projet.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Tache implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titre;
	private String description;
	private Date dateEcheance;
	
	@Enumerated(EnumType.STRING)
    private Statut statut;
	
	public enum Statut {
        EN_ATTENTE,
        EN_COURS,
        TERMINEE
    }
	
	@ManyToOne
	private Projet projet;
	
	@OneToMany(mappedBy = "tache")
	private List<Etiquette> ListEtiquette = new ArrayList<>();

}
