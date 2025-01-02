package tn.itbs.projet.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class Utilisateur implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private String email;
}
