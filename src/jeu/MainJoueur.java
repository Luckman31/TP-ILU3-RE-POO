package jeu;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;

public class MainJoueur implements Iterable<Carte> {
	private List<Carte> main = new LinkedList<>();

	public void prendre(Carte carte) {
		main.add(carte);
	}

	public void jouer(Carte carte) {
		assert main.contains(carte);
		main.remove(carte);
	}

	@Override
	public String toString() {
		return "Main du joueur : " + main;
	}

	@Override
	public Iterator<Carte> iterator() {
		return main.iterator();
	}
}
