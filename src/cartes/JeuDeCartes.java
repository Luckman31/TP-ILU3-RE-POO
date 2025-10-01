package cartes;

public class JeuDeCartes {
	private Configuration[] typesDeCartes= new Configuration[] { new Configuration(new Borne(25), 10),
				new Configuration(new Borne(50), 10), new Configuration(new Borne(75), 10),
				new Configuration(new Borne(100), 12), new Configuration(new Borne(200), 4),
				new Configuration(new Parade(Type.FEU), 14), new Configuration(new Parade(Type.LIMITE), 6),
				new Configuration(new Parade(Type.ESSENCE), 6), new Configuration(new Parade(Type.CREVAISON), 6),
				new Configuration(new Parade(Type.ACCIDENT), 6), new Configuration(new Attaque(Type.FEU), 5),
				new Configuration(new Attaque(Type.LIMITE), 4), new Configuration(new Attaque(Type.ESSENCE), 3),
				new Configuration(new Attaque(Type.CREVAISON), 3), new Configuration(new Attaque(Type.ACCIDENT), 3),
				new Configuration(new Botte(Type.FEU), 1), new Configuration(new Botte(Type.ESSENCE), 1),
				new Configuration(new Botte(Type.CREVAISON), 1), new Configuration(new Botte(Type.ACCIDENT), 1) };

	private static class Configuration {
		private Carte carte;
		private int nbExemplaires;

		private Configuration(Carte carte, int nbExemplaires) {
			this.carte = carte;
			this.nbExemplaires = nbExemplaires;
		}

		private Carte getCarte() {
			return carte;
		}

		private int getNbExemplaires() {
			return nbExemplaires;
		}

	}

	public Carte[] donnerCartes() {
		int index = 0;
		for (Configuration config : typesDeCartes) {
			index += config.getNbExemplaires();
		}
		Carte[] cartes = new Carte[index];
		int indexCartes = 0;
		for (Configuration config : typesDeCartes) {
			for (int i = 0; i < config.getNbExemplaires(); i++) {
				cartes[indexCartes] = config.getCarte();
				indexCartes++;
			}
		}
		return cartes;
	}

	public String affichageJeuCartes() {
		StringBuilder chaine = new StringBuilder();
		for (Configuration config : typesDeCartes) {
			chaine.append(config.getNbExemplaires() + " " + config.getCarte().toString() + "\n");
		}
		return chaine.toString();
	}

	public boolean checkCount() {
		Carte[] cartes = donnerCartes();

		for (Configuration config : typesDeCartes) {
			Carte carte = config.getCarte();
			int expectedCount = config.getNbExemplaires();
			int actualCount = 0;

			for (int i = 0; i < cartes.length; i++) {
				if (cartes[i].equals(carte)) {
					actualCount++;
				}
			}

			if (actualCount != expectedCount) {
				System.out.println("Erreur: La carte " + carte.toString() + " a " + actualCount
						+ " exemplaires, attendu : " + expectedCount);
				return false;
			}
		}

		return true;
	}

}
