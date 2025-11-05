package utils;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random rand = new Random();

	private GestionCartes() {
		throw new IllegalStateException("Utility class");
	}

	public static <E> E extraire(List<E> liste) {
		if (liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne peut pas etre vide.");
		}
		int index = rand.nextInt(liste.size());
		return liste.remove(index);

	}

	public static <E> E extraireIterateur(List<E> liste) {
		if (liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne peut pas etre vide.");
		}

		int index = rand.nextInt(liste.size());
		ListIterator<E> iterateur = liste.listIterator(index);
		E elem = iterateur.next();
		iterateur.remove();
		return elem;

	}

	public static <E> List<E> melanger(List<E> liste) {
		List<E> nouvelleListe = new ArrayList<>();
		while (!liste.isEmpty()) {
			nouvelleListe.add(extraire(liste));
		}
		return nouvelleListe;
	}

	public static <E> boolean verifierMelange(List<E> liste1, List<E> liste2) {
		if (liste1.size() != liste2.size()) {
			return false;
		}
		for (E element : liste1) {
			if (Collections.frequency(liste1, element) != Collections.frequency(liste2, element)) {
				return false;
			}
		}
		return true;
	}

	public static <E> List<E> rassembler(List<E> liste) {

		List<E> rassembler = new ArrayList<>();
		for (E elem : liste) {
			if (!rassembler.contains(elem)) {
				ajouterElementsEgaux(liste, rassembler, elem);
			}
		}
		return rassembler;
	}
	private static <E> void ajouterElementsEgaux(List<E> liste, List<E> rassembler, E valeur) {
        for (E elem : liste) {
            if (elem.equals(valeur)) {
            	rassembler.add(elem);
            }
        }
    }

	public static <E> boolean verifierRassemblement(List<E> liste) {
		if (liste.isEmpty()) {
			return true;
		}
		E valeurPrecedente = null;
		for (ListIterator<E> it1 = liste.listIterator(); it1.hasNext();) {
			E valeurActuelle = it1.next();
			int index = it1.nextIndex();
			if (valeurPrecedente != null && !valeurActuelle.equals(valeurPrecedente)) {
				return !repetition(valeurPrecedente, liste, index);
			}
			valeurPrecedente = valeurActuelle;
		}
		return true;
	}

	public static <E> boolean repetition(E element, List<E> liste, int index) {
		for (ListIterator<E> it2 = liste.listIterator(index); it2.hasNext();) {
			if (element.equals(it2.next())) {
				return true;
			}
		}
		return false;
	}
}
