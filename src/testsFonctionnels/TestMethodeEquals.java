package testsFonctionnels;

import cartes.Attaque;
import cartes.Borne;
import cartes.Parade;
import cartes.Type;

public class TestMethodeEquals {
	public static void main(String[] args) {
		Borne borne1=new Borne(25);
		Borne borne2=new Borne(25);
		Attaque attaque1=new Attaque(Type.FEU);
		Attaque attaque2=new Attaque(Type.FEU);
		
		Attaque attaque3=new Attaque(Type.FEU);
		Parade parade1=new Parade(Type.FEU);
		System.out.println("Deux cartes de 25km sont identiques ? "+borne1.equals(borne2));
		System.out.println("Deux cartes de feux rouge sont identiques ? "+attaque1.equals(attaque2));
		System.out.println("La carte feu rouge et la carte feu vert sont identiques ? "+attaque3.equals(parade1));
	}
}
