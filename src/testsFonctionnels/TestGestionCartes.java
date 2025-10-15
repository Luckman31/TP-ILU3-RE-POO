package testsFonctionnels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

public class TestGestionCartes {
	public static void main(String[] args) {
		// Initialisation du jeu de cartes
		JeuDeCartes jeu = new JeuDeCartes();
		List<Carte> listeCarteNonMelangee = new LinkedList<>();
		for (Carte carte : jeu.donnerCartes()) {
			listeCarteNonMelangee.add(carte);
		}
		List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
		System.out.println("Liste originale : " + listeCartes);
		listeCartes = GestionCartes.melanger(listeCartes);
		System.out.println("Liste mélangée : " + listeCartes);
		boolean melangeCorrect = GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes);
		System.out.println("Liste mélangée sans erreur ? " + melangeCorrect);
		listeCartes = GestionCartes.rassembler(listeCartes);
		System.out.println("Liste après rassemblement : " + listeCartes);

		boolean rassemblementCorrect = GestionCartes.verifierRassemblement(listeCartes);
		System.out.println("Liste rassemblée sans erreur ? " + rassemblementCorrect);

		testerRassemblement(Arrays.asList());
		testerRassemblement(Arrays.asList(1, 1, 2, 1, 3));
		testerRassemblement(Arrays.asList(1, 4, 3, 2));
		testerRassemblement(Arrays.asList(1, 1, 2, 3, 1));
	}

	public static <C> void testerRassemblement(List<C> liste) {
		System.out.println("\nListe originale : " + liste);
		List<C> copie = new ArrayList<>(liste);

		copie = GestionCartes.rassembler(copie);
		System.out.println("Liste après rassemblement : " + copie);

		boolean estRassemble = GestionCartes.verifierRassemblement(copie);
		System.out.println("Liste rassemblée sans erreur ? " + estRassemble);
	}
}