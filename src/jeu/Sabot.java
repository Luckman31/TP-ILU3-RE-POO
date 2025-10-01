package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot implements Iterable<Carte> {
	private Carte[] cartes;
	private int nbCartes;
	private static final int CAPACITE_MAX = 52;
	private int nbOperations = 0;

	public Sabot(Carte[] cartes) {
		this.cartes = cartes;
		this.nbCartes = cartes.length;
	}

	public boolean estVide() {
		return nbCartes == 0;
	}

	public void ajouterCarte(Carte carte) {
		if (nbCartes >= CAPACITE_MAX) {
			throw new IllegalStateException("Le sabot est plein, impossible d'ajouter la carte.");
		}
		cartes[nbCartes] = carte;
		nbCartes++;
		nbOperations++;
	}

	@Override
	public Iterator<Carte> iterator() {
		return new Iterateur();
	}

	private class Iterateur implements Iterator<Carte> {
		private int indiceIterateur = 0;
		private boolean nextEffectue = false;
		private int nombreOperationsReference = nbOperations;

		@Override
		public boolean hasNext() {
			return indiceIterateur < nbCartes;
		}

		@Override
		public Carte next() {
			verificationConcurrence();
			if (hasNext()) {
				Carte carte = cartes[indiceIterateur];
				indiceIterateur++;
				nextEffectue = true;
				return carte;
			} else {
				throw new NoSuchElementException();
			}
		}

		private void verificationConcurrence() {
			if (nbOperations != nombreOperationsReference) {
				throw new ConcurrentModificationException();
			}
		}

		@Override
		public void remove() {
			verificationConcurrence();
			if (nbCartes < 1 || !nextEffectue) {
				throw new IllegalStateException();
			}
			for (int i = indiceIterateur - 1; i < nbCartes - 1; i++) {
				cartes[i] = cartes[i + 1];
			}
			nextEffectue = false;
			indiceIterateur--;
			nbCartes--;
			nbOperations++;
			nombreOperationsReference++;
		}

	}

	public Carte piocher() {
		if (estVide()) {
			throw new NoSuchElementException("Le sabot est vide.");
		}
		Iterator<Carte> it = iterator();
		if (it.hasNext()) {
			Carte cartePioche = it.next();
			it.remove();
			return cartePioche;
		}
		return null;

	}

}
