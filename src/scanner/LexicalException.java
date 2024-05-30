package scanner;

import java.lang.Exception;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Eccezione lessicale che estende la classe Exception
 */
public class LexicalException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Costruisce l'eccezione con la stringa che causa l'errore, la riga e il carattere che ha causato l'errore
	 * 
	 * @param string	Stringa che causa l'errore
	 * @param row		Riga in cui si trova l'errore
	 * @param error		Carattere che ha causato l'errore
	 */
	public LexicalException(String string, int row, char error) {
		super("Token: " + string + " - Riga: " + row + " - Carattere: " + error);
	}
	
	/**
	 * Costruisce l'eccezione con la stringa che causa l'errore e la riga
	 * 
	 * @param string	Stringa che causa l'errore
	 * @param row		Riga in cui si trova l'errore
	 */
	public LexicalException(String string, int row) {
		super("Token: " + string + " - Riga: " + row);
	}
	
	/**
	 * Costruisce l'eccezione con la stringa che causa l'errore e il carattere che ha causato l'errore
	 * 
	 * @param string	Stringa che causa l'errore
	 * @param causa		Carattere che ha causato l'errore
	 */
	public LexicalException(String string, Throwable causa) {
		super(string, causa);
	}
}
