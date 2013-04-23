package com.example.capdc;

public class espece {

	private int id;
	private String libelle;
	
	public espece() {
	}
	public espece(String libelle) {
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
