package token;

/**
 * @author Nicholas Ternullo 20045859
 *
 * Classe che rappresenta un token
 */
public class Token {

	/**
	 * Numero della riga dove si trova il Token
	 */
	private int riga;
	
	/**
	 * Tipo del Token
	 */
	private TokenType tipo;
	
	/**
	 * Valore del Token
	 */
	private String val;
	
	/**
	 * Costruttore del Token con il tipo, la riga e il valore
	 * 
	 * @param tipo	Tipo del Token
	 * @param riga	Riga dove si trova il Token
	 * @param val	Valore del Token
	 */
	public Token(TokenType tipo, int riga, String val) {
		this.riga = riga;
		this.tipo = tipo;
		this.val = val;
	}
	
	/**
	 * Costruttore del Token con il tipo e la riga, il valore è assegnato a null
	 * 
	 * @param tipo	Tipo del Token
	 * @param riga	Riga dove si trova il Token
	 */
	public Token(TokenType tipo, int riga) {
		this.riga = riga;
		this.tipo = tipo;
		this.val = null;
	}
	
	/**
	 * @return	Il numero della riga a cui si trova il Token
	 */
	public int getRiga() {
		return riga;
	}
	
	/**
	 * @return	Il tipo del Token
	 */
	public TokenType getTipo() {
		return tipo;
	}
	
	/**
	 * @return	Il valore del Token
	 */
	public String getVal() {
		return val;
	}
    
	/**
	 * @return Una stringa nel formato "<tipo,r:riga,valore>"
	 * 		   Se il valore è null la stringa tornata è nel formato "<tipo,r:riga>"
	 */
	public String toString() {
		if(getVal() != null)
			return "<"+getTipo()+",r:"+getRiga()+","+getVal()+">";
		else
			return "<"+getTipo()+",r:"+getRiga()+">";
	}
}
