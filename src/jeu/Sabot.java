package jeu;

import java.util.Iterator;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes;
	private static final int CAPACITE_MAX = 52;
	private int nbOperations=0;
	private boolean nextEffectue=false;
	public Sabot(Carte[] cartes) {
		this.cartes=cartes;
		this.nbCartes=cartes.length;
	}
	public boolean estVide() {
		return nbCartes==0;
	}
	public void ajouterCarte(Carte carte) {
		if (nbCartes >= CAPACITE_MAX) {
			throw new IllegalStateException("Le sabot est plein, impossible d'ajouter la carte.");
		}
		cartes[nbCartes]=carte;
		nbCartes++;
	}
	@Override
	public Iterator<Carte> iterator(){
		return new Iterateur();
	}
	private class Iterateur implements Iterator<Carte>{

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Carte next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}
