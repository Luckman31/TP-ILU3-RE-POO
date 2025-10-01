package cartes;

public class Borne extends Carte {
	private int km;

	public Borne(int km) {
		this.km = km;
	}

	public String toString() {
		StringBuilder chaine = new StringBuilder();
		chaine.append(km + "KM");
		return chaine.toString();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && km==((Borne)obj).km;
	}

}
