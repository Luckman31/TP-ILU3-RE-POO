package jeu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cartes.Attaque;
import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Cartes;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;
import cartes.Type;

public class ZoneDeJeu {
	private List<Limite> pileLimites = new LinkedList<>();
	private List<Bataille> pileBatailles = new LinkedList<>();
	private List<Borne> collectionBorne = new ArrayList<>();
	private Set<Botte> ensembleBottes = new HashSet<>();

	public int donnerLimitationVitesse() {
		if (pileLimites.isEmpty()) {
			return 200;
		}
		if (estPrioritaire()) {
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
		} else if (c instanceof Botte botte) {
			ensembleBottes.add(botte);
		}
	}

	public boolean peutAvancer() {
		if (!pileBatailles.isEmpty()) {
			Bataille carteSommet = pileBatailles.get(0);
			if (carteSommet.equals(Cartes.FEU_VERT)) {
				return true;
			}
			if (carteSommet instanceof Parade && estPrioritaire()) {
				return true;
			}
			if (carteSommet.equals(Cartes.FEU_ROUGE) && estPrioritaire()) {
				return true;
			}
			if (carteSommet instanceof Attaque attaque && estPrioritaire()) {
				
				for (Botte botte : ensembleBottes) {
					if (botte.getType() == attaque.getType()) {
						return true;
					}
				}
			}
		} else {
			if (estPrioritaire()) {
				return true;
			}
		}
		return false;
	}

	private boolean estDepotFeuVertAutorise() {
		if (pileBatailles.isEmpty()) {
			return true;
		}
		Carte sommet = pileBatailles.get(0);
		if (sommet.equals(Cartes.FEU_ROUGE)) {
			return true;
		}
		if (!sommet.equals(Cartes.FEU_VERT)) {
			return true;
		}
		return false;
	}

	private boolean estDepotBorneAutorise(Borne borne) {

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

	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite) {
			return pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite;
		} else {
			if (limite instanceof FinLimite && (pileLimites.get(0) instanceof DebutLimite)) {

				return true;
			}
		}
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {

		if (bataille instanceof Attaque) {
			return peutAvancer();
		}
		if (bataille instanceof Parade parade) {
			if (parade.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else {
				if (!pileBatailles.isEmpty()) {
					Carte derniere = pileBatailles.get(0);
					return derniere instanceof Attaque attaque && (attaque).getType().equals((bataille).getType());
				}
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

	public boolean estPrioritaire() {
		return ensembleBottes.contains(Cartes.PRIORITAIRE);
	}
}
