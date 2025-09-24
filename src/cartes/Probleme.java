package cartes;

public abstract class Probleme extends Carte {
	private Type type;
	protected Probleme(Type type) {
		this.type=type;
	}
	private Type getType() {
		return type;
	}
	
}
