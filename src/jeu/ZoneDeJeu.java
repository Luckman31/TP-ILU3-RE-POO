package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new LinkedList<>();
	private List<Bataille> pileBatailles = new LinkedList<>();
	private List<Borne> collectionBorne = new ArrayList<>();

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty()) {
			return 200;
		}
		Limite carteSommet = pileLimites.get(0);
		if (carteSommet instanceof FinLimite) {
			return 200;
		}
		return 50;

	}

	public int donnerKmParcourus() {
		int totalKm = 0;
		for (Borne borne : collectionBorne) {
			totalKm += borne.getKm();
		}
		return totalKm;
	}

	public void deposer(Carte c) {
		if (c instanceof Borne borne) {
			collectionBorne.add(borne);
		} else if (c instanceof Limite limite) {
			pileLimites.add(0, limite);
		} else if (c instanceof Bataille bataille) {
			pileBatailles.add(0, bataille);
		}
	}

	public boolean peutAvancer() {
		if (!pileBatailles.isEmpty()) {
			Bataille carteSommet = pileBatailles.get(0);
			if (carteSommet.equals(Cartes.FEU_VERT)) {
				return true;
			}
		}
		return false;
	}

	public boolean estDepotFeuVertAutorise() {
		if (pileBatailles.isEmpty()) {
			return true;
		}
		Carte sommet = pileBatailles.get(0);
		if (sommet.equals(Cartes.FEU_ROUGE)) {
			return true;
		}
		if (sommet.equals(Cartes.FEU_VERT)) {
			return false;
		}
		return false;
	}

	public boolean estDepotBorneAutorise(Borne borne) {

		
		int vitesseLimite = donnerLimitationVitesse();
		if (borne.getKm() > vitesseLimite) {
			return false;
		}
		int totalKm = 0;
		for (Borne borne2 : collectionBorne) {
			totalKm += borne2.getKm();
		}

		return totalKm + borne.getKm() < 1000 && peutAvancer();
	}

	public boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite;
		} else {
			if (limite instanceof FinLimite && (pileLimites.get(0) instanceof DebutLimite)) {

				return true;
			}
		}
		return false;
	}

	public boolean estDepotBatailleAutorise(Bataille bataille) {
		//TODO peutAvancer() et estDepotFeuVertAutorise()
		if (bataille instanceof Attaque) {
			return !pileBatailles.isEmpty() && !(pileBatailles.get(0) instanceof Attaque);
		}
		if (bataille instanceof Parade parade) {
			if (parade.equals(Cartes.FEU_VERT)) {
				if (!pileBatailles.isEmpty()) {
					Carte derniere = pileBatailles.get(0);
					return derniere instanceof Attaque
							|| (Parade) derniere instanceof Parade && !(((Parade) derniere).equals(Cartes.FEU_VERT));
				} else {
					return true;
				}
			} else if (!pileBatailles.isEmpty()) {
				Carte derniere = pileBatailles.get(0);
				return derniere instanceof Attaque attaque && (attaque).equals(Cartes.FEU_ROUGE);
			}
		}
		return false;
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) {
			return estDepotBorneAutorise(borne);
		}
		if (carte instanceof Limite limite) {
			return estDepotLimiteAutorise(limite);
		}
		if (carte instanceof Bataille bataille) {
			return estDepotBatailleAutorise(bataille);
		}
		return false;
	}
}
