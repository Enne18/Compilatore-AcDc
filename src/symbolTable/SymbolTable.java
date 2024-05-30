package symbolTable;

import java.util.HashMap;

import ast.LangType;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che implementa la Symbol Table
 * Tabella dei simboli per le informazioni sugli identificatori
 */
public class SymbolTable {
	
	/**
	 * HashMap per associare l'infprmazione all'identificatore
	 */
	private static HashMap<String, Attributes> table;
	
	/**
	 * @author Nicholas Ternullo 20045859
	 *
	 * Classe che rappresenta gli attributi degli identificatori
	 */
	public static class Attributes {
		
		/**
		 * Tipo dell'identificatore
		 */
		private LangType tipo;
		
		/**
		 * Registro associato all'identificatore
		 */
		private char registro;

		/**
		 * Costruisce l'attributo associando il tipo
		 * 
		 * @param tipo Tipo dell'identificatore
		 */
		public Attributes (LangType tipo) {
			this.tipo = tipo;
		}
		
		/**
		 * Costruisce l'attributo
		 * 
		 * @param tipo		Tipo dell'identificatore
		 * @param registro	Registro dell'identificatore
		 */
		public Attributes (LangType tipo, char registro) {
			this.tipo = tipo;
			this.registro = registro;
		}

		/**
		 * Ritorna il tipo dell'identificatore
		 * 
		 * @return	Il tipo dell'identificatore
		 */
		public LangType getTipo() {
			return tipo;
		}

		/**
		 * Ritorna il registro dell'identificatore
		 * 
		 * @return	Il registro dell'identificatore
		 */
		public char getRegistro() {
			return registro;
		}

		/**
		 * Costruisce l'attributo associando il registro
		 * 
		 * @param registro	Il registro dell'identificatore
		 */
		public void setRegistro(char registro) {
			this.registro = registro;
		}
		
	}

	/**
	 * Inizializza l'HashMap
	 */
	public static void init() {
		table = new HashMap<String, Attributes>();
	}

	/**
	 * Inserisce l'identificatore nella Symbol Table
	 * 
	 * @param id		Nome dell'identificatore da inserire nella SymbolTable
	 * @param entry		Attributi dell'identificatore
	 * @return			True se l'identificatore è nuovo, False altrimenti
	 */
	public static boolean enter(String id, Attributes entry) {
		if(table.containsKey(id)) {
			return false;
		}
		else {
			table.put(id, entry);
			return true;
		}
	}

	/**
	 * Ritorna gli attributi di un identificatore, se non esiste ritorna null
	 * 
	 * @param id	Nome dell'identificatore su cui cercare gli attributi
	 * @return		Gli attributi dell'identificatore, se non esiste ritorna null
	 */
	public static Attributes lookup(String id) {
		return table.get(id);
	}

	/**
	 * @return	La tabella in formato stringa
	 */
	public static String toStr() {
		return table.toString();
	}

	/**
	 * @return	La dimensione della tabella
	 */
	public static int size() {
		return table.size();
	}
}