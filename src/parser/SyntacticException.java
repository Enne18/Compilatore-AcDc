package parser;

import java.lang.Exception;
import token.TokenType;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Eccezione sintattica che estende la classe Exception
 */
public class SyntacticException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruisce l'eccezione con il tipo atteso, la riga e il tipo trovato
	 * 
	 * @param tipoAtteso	Tipo atteso
	 * @param riga			Riga in cui si trova l'errore
	 * @param tipoTrovato	Tipo trovato
	 */
	public SyntacticException(TokenType tipoAtteso, int riga, TokenType tipoTrovato) {
		super("Tipo atteso: " + tipoAtteso + " - Riga: " + riga + " - Tipo trovato: " + tipoTrovato);
	}
	
	/**
	 * Costruisce l'eccezione con il tipo atteso, la riga e il tipo trovato
	 * 
	 * @param tipoAtteso	Tipo atteso
	 * @param riga			Riga in cui si trova l'errore
	 * @param tipoTrovato	Tipo trovato
	 */
	public SyntacticException(String tipoAtteso, int riga, TokenType tipoTrovato) {
		super("Tipo atteso: " + tipoAtteso + " - Riga: " + riga + " - Tipo trovato: " + tipoTrovato);
	}
	
	/**
	 * Costruisce l'eccezione con il tipo atteso, la riga e il tipo trovato
	 * 
	 * @param tipoAtteso	Tipo atteso
	 * @param riga			Riga in cui si trova l'errore
	 * @param tipoTrovato	Tipo trovato
	 */
	public SyntacticException(String tipoAtteso, int riga, String tipoTrovato) {
		super("Tipo atteso: " + tipoAtteso + " - Riga: " + riga + " - Tipo trovato: " + tipoTrovato);
	}
	
	/**
	 * Costruisce l'eccezione con la stringa attesa e la causa dell'eccezione
	 * 
	 * @param string	Stringa attesa
	 * @param causa		Causa dell'eccezione
	 */
	public SyntacticException(String string, Throwable causa) {
		super(string, causa);
	}

//	public SyntacticException(String string) {
//		super(string);
//	}
	
}
