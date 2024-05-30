package symbolTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che descrive 26 registri, per ampliarne il numero basta aggiungere registri all'ArrayList
 */
public class Registri {
	
	/**
	 * Lista dei registri non ancora inizializzata
	 */
	private static ArrayList<Character> registri;

	/**
	 * Inizializza la lista con i registri disponibili
	 */
	public static void inizializza() {
		registri = new ArrayList<>(Arrays.asList(new Character[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
				'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}));
	}
	
	/**
	 * Rimuove e ritorna il primo elemento della lista
	 * 
	 * @return	Il primo registro (disponibile)
	 */
	public static Character newRegister() {
		if(registri.isEmpty()) {
			return null;
		}
		else {
			return registri.remove(0);
		}
	}
	
	/**
	 * Ritorna la quantità dei registri rimasti
	 * 
	 * @return La dimensione di registri
	 */
	public static int size() {
		return registri.size();
	}
}
