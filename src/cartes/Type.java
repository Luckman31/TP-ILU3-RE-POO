package cartes;

public enum Type {
	FEU("Feu rouge", "Feu vert", "Prioritaire"), ESSENCE("PanneEssence", "Essence", "CiterneD'essence"),
	CREVAISON("Crevaison", "RoueDeSecours", "Increvable"), ACCIDENT("Accident", "Reparations", "AsDuVolant"),
	LIMITE("Limite 50", "Fin Limite", null);

	private String attaque;
	private String parade;
	private String botte;

	private Type(String attaque, String parade, String botte) {
		this.attaque = attaque;
		this.parade = parade;
		this.botte = botte;
	}

	public String attaque() {
		return attaque;
	}

	public String parade() {
		return parade;
	}

	public String botte() {
		return botte;
	}
}
