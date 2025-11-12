package jeu;

import cartes.Carte;

public class Joueur {
	private String nom;
	private ZoneDeJeu zonedejeu = new ZoneDeJeu();
	private MainJoueur mainjoueur = new MainJoueur();

	public Joueur(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Joueur joueur) {
			return nom.equals(joueur.nom);
		}
		return false;
	}

	@Override
	public String toString() {
		return nom;
	}

	public void donner(Carte carte) {
		mainjoueur.prendre(carte);
	}

	public Carte prendreCarte(Sabot sabot) {
		if (sabot.estVide()) {
			return null;
		}
		Carte cartePiochee = sabot.piocher();
		donner(cartePiochee);
		return cartePiochee;
	}

	public int donnerKmParcourus() {
		return zonedejeu.donnerKmParcourus();
	}

	public void deposer(Carte c) {
		zonedejeu.deposer(c);
	}

	public boolean estDepotAutorise(Carte carte) {
		return zonedejeu.estDepotAutorise(carte);
	}
}
