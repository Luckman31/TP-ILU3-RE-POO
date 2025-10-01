package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class GestionCartes {
	private static Random rand = new Random();
	public static <E> E extraire(List<E> liste){
		if(liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne peut pas etre vide.");
		}
		int index=rand.nextInt(liste.size());
		return liste.remove(index);
		
	}
	public static<E> E extraireIterateur(List<E> liste) {
		if(liste.isEmpty()) {
			throw new IllegalArgumentException("La liste ne peut pas etre vide.");
		}
		
		int index=rand.nextInt(liste.size());
		ListIterator<E> iterateur=liste.listIterator(index);
		E elem=iterateur.next();
		iterateur.remove();
		return elem;
		
	}
	public static <E> List<E> melanger(List<E> liste) {
		List<E> nouvelleListe=new ArrayList<>();
		while(!liste.isEmpty()) {
			nouvelleListe.add(extraire(liste));
		}
		return nouvelleListe;
	}
	public static <E> boolean verifierMelange(List<E> liste1,List<E> liste2) {
		if(liste1.size()!=liste2.size()) {
			return false;
		}
		for(E element:liste1) {
			if(Collections.frequency(liste1, element)!=Collections.frequency(liste2, element)) {
				return false;
			}
		}
		return true;
	}
}
